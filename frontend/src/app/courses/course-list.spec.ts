import { provideHttpClient } from '@angular/common/http';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Course } from './course.model';
import { CourseList } from './course-list';

const COURSES: Course[] = [
  { id: 1, title: 'Sachkunde Pflanzenschutz', startDate: '2026-11-05', location: 'Münster', capacity: 12, freePlaces: 3 },
  { id: 2, title: 'Betriebsnachfolge', startDate: '2026-11-18', location: 'Bonn', capacity: 2, freePlaces: 0 },
];

describe('CourseList', () => {
  let http: HttpTestingController;
  let fixture: ComponentFixture<CourseList>;
  let el: HTMLElement;

  function type(selector: string, value: string): void {
    const input = el.querySelector<HTMLInputElement>(selector)!;
    input.value = value;
    input.dispatchEvent(new Event('input'));
  }

  beforeEach(async () => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    http = TestBed.inject(HttpTestingController);
    fixture = TestBed.createComponent(CourseList);
    el = fixture.nativeElement;
    TestBed.tick();
    http.expectOne('/api/courses').flush(COURSES);
    await fixture.whenStable();
  });

  afterEach(() => http.verify());

  it('zeigt freie Plätze und ausgebuchte Kurse', () => {
    const items = el.querySelectorAll('.course');

    expect(items[0].textContent).toContain('3 von 12 Plätzen frei');
    expect(items[1].textContent).toContain('Ausgebucht');
    expect(items[1].querySelector('button')?.textContent).toContain('Auf die Warteliste');
  });

  it('meldet für einen Kurs an und lädt die Liste neu', async () => {
    el.querySelector<HTMLButtonElement>('.course button')!.click();
    await fixture.whenStable();

    type('input[autocomplete=name]', 'Clara Test');
    type('input[type=email]', 'clara@example.org');
    await fixture.whenStable();
    el.querySelector<HTMLButtonElement>('button[type=submit]')!.click();

    const req = http.expectOne('/api/courses/1/registrations');
    expect(req.request.body).toEqual({ name: 'Clara Test', email: 'clara@example.org' });
    req.flush({
      id: 7,
      courseId: 1,
      name: 'Clara Test',
      email: 'clara@example.org',
      status: 'CONFIRMED',
      waitlistPosition: null,
    });
    TestBed.tick();
    http.expectOne('/api/courses').flush(COURSES);
    await fixture.whenStable();

    expect(el.querySelector('.confirmation')?.textContent).toContain('Danke, Clara Test!');
  });

  it('setzt bei ausgebuchtem Kurs auf die Warteliste und nennt den Platz', async () => {
    el.querySelectorAll<HTMLElement>('.course')[1].querySelector('button')!.click();
    await fixture.whenStable();
    type('input[autocomplete=name]', 'Clara Test');
    type('input[type=email]', 'clara@example.org');
    await fixture.whenStable();

    const submit = el.querySelector<HTMLButtonElement>('button[type=submit]')!;
    expect(submit.textContent).toContain('Auf die Warteliste setzen');
    submit.click();

    http.expectOne('/api/courses/2/registrations').flush({
      id: 8,
      courseId: 2,
      name: 'Clara Test',
      email: 'clara@example.org',
      status: 'WAITLISTED',
      waitlistPosition: 3,
    });
    TestBed.tick();
    http.expectOne('/api/courses').flush(COURSES);
    await fixture.whenStable();

    expect(el.querySelector('.confirmation')?.textContent).toContain('Sie stehen auf Platz 3 der Warteliste.');
  });
});

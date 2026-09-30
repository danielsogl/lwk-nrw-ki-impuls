import { DatePipe } from '@angular/common';
import { ChangeDetectionStrategy, Component, inject, signal } from '@angular/core';
import { CourseApi } from './course-api';
import { Registration } from './course.model';
import { RegistrationForm } from './registration-form';

@Component({
  selector: 'app-course-list',
  imports: [DatePipe, RegistrationForm],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './course-list.html',
})
export class CourseList {
  private readonly api = inject(CourseApi);

  protected readonly courses = this.api.courses;
  protected readonly selectedCourseId = signal<number | null>(null);
  protected readonly confirmation = signal<string | null>(null);

  protected select(courseId: number): void {
    this.confirmation.set(null);
    this.selectedCourseId.set(courseId);
  }

  protected onRegistered(registration: Registration): void {
    this.selectedCourseId.set(null);
    this.confirmation.set(`Danke, ${registration.name}! Ihre Anmeldung ist bestätigt.`);
    this.courses.reload();
  }
}

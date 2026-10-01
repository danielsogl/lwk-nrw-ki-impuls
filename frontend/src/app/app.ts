import { Component } from '@angular/core';
import { CourseList } from './courses/course-list';

@Component({
  selector: 'app-root',
  imports: [CourseList],
  template: `
    <header>
      <h1>Seminaranmeldung</h1>
      <p>Fortbildungen und Lehrgänge</p>
    </header>
    <main>
      <app-course-list />
    </main>
  `,
})
export class App {}

import { HttpClient, httpResource } from '@angular/common/http';
import { Service, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Course, Registration, RegistrationRequest } from './course.model';

@Service()
export class CourseApi {
  private readonly http = inject(HttpClient);

  readonly courses = httpResource<Course[]>(() => '/api/courses', { defaultValue: [] });

  register(courseId: number, request: RegistrationRequest): Observable<Registration> {
    return this.http.post<Registration>(`/api/courses/${courseId}/registrations`, request);
  }
}

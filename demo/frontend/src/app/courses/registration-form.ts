import { HttpErrorResponse } from '@angular/common/http';
import { ChangeDetectionStrategy, Component, inject, input, output, signal } from '@angular/core';
import { email, form, FormField, required } from '@angular/forms/signals';
import { CourseApi } from './course-api';
import { Course, Registration } from './course.model';

@Component({
  selector: 'app-registration-form',
  imports: [FormField],
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `
    <form (submit)="submit($event)" novalidate>
      <label>
        Name
        <input [formField]="registrationForm.name" autocomplete="name" />
      </label>
      <label>
        E-Mail
        <input type="email" [formField]="registrationForm.email" autocomplete="email" />
      </label>
      @if (error()) {
        <p class="error" role="alert">{{ error() }}</p>
      }
      <button type="submit" [disabled]="registrationForm().invalid() || pending()">Verbindlich anmelden</button>
    </form>
  `,
})
export class RegistrationForm {
  private readonly api = inject(CourseApi);

  readonly course = input.required<Course>();
  readonly registered = output<Registration>();

  protected readonly model = signal({ name: '', email: '' });
  protected readonly registrationForm = form(this.model, (f) => {
    required(f.name, { message: 'Bitte einen Namen angeben.' });
    required(f.email, { message: 'Bitte eine E-Mail-Adresse angeben.' });
    email(f.email, { message: 'Das ist keine gültige E-Mail-Adresse.' });
  });
  protected readonly pending = signal(false);
  protected readonly error = signal<string | null>(null);

  protected submit(event: Event): void {
    event.preventDefault();
    if (this.registrationForm().invalid()) {
      return;
    }
    this.pending.set(true);
    this.error.set(null);
    this.api.register(this.course().id, this.model()).subscribe({
      next: (registration) => {
        this.pending.set(false);
        this.registered.emit(registration);
      },
      error: (err: HttpErrorResponse) => {
        this.pending.set(false);
        this.error.set(err.status === 409 ? 'Der Kurs ist leider ausgebucht.' : 'Die Anmeldung ist fehlgeschlagen.');
      },
    });
  }
}

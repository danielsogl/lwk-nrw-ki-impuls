// Spiegelt die Schemas aus api/openapi.yaml. Änderungen immer zuerst im Vertrag.

export interface Course {
  id: number;
  title: string;
  /** ISO-Datum, z. B. 2026-11-05 */
  startDate: string;
  location: string;
  capacity: number;
  freePlaces: number;
}

export interface RegistrationRequest {
  name: string;
  email: string;
}

export interface Registration {
  id: number;
  courseId: number;
  name: string;
  email: string;
}

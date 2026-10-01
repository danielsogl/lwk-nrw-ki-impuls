INSERT INTO course (id, title, start_date, location, capacity) VALUES
  (1, 'Sachkunde Pflanzenschutz – Fortbildung', DATE '2026-11-05', 'Münster', 12),
  (2, 'Betriebsnachfolge rechtssicher planen', DATE '2026-11-18', 'Bonn', 2),
  (3, 'Digitale Ackerschlagkartei in der Praxis', DATE '2026-12-02', 'online', 20);

INSERT INTO registration (course_id, name, email, created_at) VALUES
  (2, 'Anna Beispiel', 'anna@example.org', TIMESTAMP '2026-09-01 09:00:00'),
  (2, 'Ben Muster', 'ben@example.org', TIMESTAMP '2026-09-02 10:30:00');
ALTER TABLE course ALTER COLUMN id RESTART WITH 100;

DELETE FROM package_update;
DELETE FROM package;
DELETE FROM provider;


WITH newsalt AS (SELECT gen_salt('bf', 8) as salt)
INSERT INTO provider(provider_name, password, salt)
  VALUES  ('admin', crypt('admin', (SELECT salt from newsalt)), (SELECT salt from newsalt)),
          ('provider', crypt('provider', (SELECT salt from newsalt)), (SELECT salt from newsalt));

INSERT INTO package(provider_name, source, destination)
  VALUES
    ('admin', '3762 Sycamore Fork Road, Hopkins, MN, 55343, United States', '0189 Fanny Center, Apt. 412, 84110, North Revafort, Nevada, United States'),
    ('admin', '1640 Δεσποτόπουλος Shoal, Apt. 602, 95684, Μαρκόπουλοςshire, South Dakota, Greece', '296 Αξιώτης Freeway, Apt. 706, 51286-1224, Μαυρογένηςville, Maryland, Greece'),
    ('provider', '6333 Willms Estate, Apt. 944, 76443, Georgiannaborough, Wyoming, Ireland', '678 Clay Mission, Suite 338, 48511-2477, Whitefurt, Texas, Ireland'),
    ('provider', '353 Lauriane Drive, Apt. 618, 6705, Weinfelden, Saint-Gall, Switzerland', '1718 Kertzmann Falls, Suite 155, 8907, Crissier, Lucerne, Switzerland'),
    ('provider', 'пл. Школьная, 567, кв. 707, 819630, Воронеж, Калужская область, Russia', 'Пушкина площадь, 973, кв. 429, 848504, Волгоград, Тульская область, Russia');



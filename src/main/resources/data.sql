--   CUSTOMERS
INSERT INTO customers (id, name, company) VALUES
  (1, 'Matteo Baiguini', 'MB-Solid-Consulting'),
  (2, 'Martin Fowler', 'ThoughtWorks'),
  (3, 'Erik Gamma', 'Microsoft');

--   BOOKS
INSERT INTO books (id, title, author, language, customer_id) VALUES
  (1, 'Clean Code', 'Uncle Bob', 'EN', 1),
  (2, 'Clean Architectures', 'Uncle Bob', 'EN', 1),
  (3, 'Building Microservices', 'Sam Newman', 'EN', 2);

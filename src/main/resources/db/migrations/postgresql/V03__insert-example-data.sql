-- Registros para a categoria 'Lanche'
INSERT INTO prd_product (deleted, price, category, creation_date, last_update_date, name)
VALUES (false, 10.99, (SELECT id FROM cat_category WHERE name = 'Lanche'), NOW(), NOW(), 'Hambúrguer');

INSERT INTO prd_product (deleted, price, category, creation_date, last_update_date, name)
VALUES (false, 8.99, (SELECT id FROM cat_category WHERE name = 'Lanche'), NOW(), NOW(), 'Sanduíche de Frango');

-- Registros para a categoria 'Acompanhamento'
INSERT INTO prd_product (deleted, price, category, creation_date, last_update_date, name)
VALUES (false, 3.99, (SELECT id FROM cat_category WHERE name = 'Acompanhamento'), NOW(), NOW(), 'Batata Frita');

INSERT INTO prd_product (deleted, price, category, creation_date, last_update_date, name)
VALUES (false, 2.99, (SELECT id FROM cat_category WHERE name = 'Acompanhamento'), NOW(), NOW(), 'Nuggets');

-- Registros para a categoria 'Bebida'
INSERT INTO prd_product (deleted, price, category, creation_date, last_update_date, name)
VALUES (false, 4.99, (SELECT id FROM cat_category WHERE name = 'Bebida'), NOW(), NOW(), 'Refrigerante');

INSERT INTO prd_product (deleted, price, category, creation_date, last_update_date, name)
VALUES (false, 2.49, (SELECT id FROM cat_category WHERE name = 'Bebida'), NOW(), NOW(), 'Suco');

-- Registros para a categoria 'Sobremesa'
INSERT INTO prd_product (deleted, price, category, creation_date, last_update_date, name)
VALUES (false, 6.99, (SELECT id FROM cat_category WHERE name = 'Sobremesa'), NOW(), NOW(), 'Sorvete');

INSERT INTO prd_product (deleted, price, category, creation_date, last_update_date, name)
VALUES (false, 4.49, (SELECT id FROM cat_category WHERE name = 'Sobremesa'), NOW(), NOW(), 'Bolo de Chocolate');
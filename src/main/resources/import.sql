INSERT INTO user (id, first_name, last_name, email, user_name, password) VALUES (1, 'Andrzej', 'Duda', 'aduda@gmail.com', 'admin', 'password');
INSERT INTO user (id, first_name, last_name, email, user_name, password) VALUES (2, 'Rafal', 'Mitula', 'rmitula@gmail.com', 'rmitula', 'password');
INSERT INTO user (id, first_name, last_name, email, user_name, password) VALUES (3, 'Kamil', 'Kowalski', 'kamilkowalski@gmail.com', 'kamil', 'password');

INSERT INTO category (id, name) VALUES (1, 'shoes');

INSERT INTO product (id, name, quanity_In_Stock, price, category_id) VALUES (1, 'Nike Air Max 1', 10, 450.99, 1);
INSERT INTO product (id, name, quanity_In_Stock, price, category_id) VALUES (2, 'Adidas Superstar', 10, 500.50, 1);
INSERT INTO product (id, name, quanity_In_Stock, price, category_id) VALUES (3, 'Converse All Star', 10, 222.22, 1);
INSERT INTO product (id, name, quanity_In_Stock, price, category_id) VALUES (4, 'Onitsuka Tiger Corsair', 350, 450.1, 1);



--
--     @Id
--     @GeneratedValue
--     private Long id;
--     @NotNull
--     private String name;
--     @NotNull
--     private Integer quanityInStock;
--     @NotNull
--     private Integer price;
--
--     @ManyToOne
--     private Category category;
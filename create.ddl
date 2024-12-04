
    create sequence T_BOOK_SEQ start with 1 increment by 50;

    create table T_BOOK (
        nb_of_pages integer,
        price numeric(38,2),
        publication_date date,
        id bigint not null,
        isbn varchar(50),
        title varchar(200),
        description varchar(1000),
        image_url varchar(255),
        primary key (id)
    );
INSERT INTO T_BOOK (id, isbn, title, image_url, price, nb_of_pages, publication_date, description) VALUES ( 1001, '1931182310', 'Advanced Java EE Development for RAD Guidebook', 'http://ecx.images-amazon.com/images/I/51bjnhlGbeL._SL160_.jpg', 79.95, 752, DATE '2011-11-21', 'Written by IBM senior field engineers and senior product development experts, this advanced book provides a solid look at the development of a range of core Java EE technologies, as well as an in-depth description of the development facilities provided by IBM Rational Application Developer version 7.5. Since the Java EE developmental platform incorporates a wide range of technologies from disparate and myriad sources, this up-to-date guidebook helps developers triumph over the complexity and depth of knowledge required to build successful architectures. Senior developers, engineers, and architects—especially those who work with Rational Application Developer and those seeking certification at the Sun-certified Java master-tier level or the IBM Rational Application Developer certified professional and certified advanced professional levels—will appreciate this convenient, single reference point.');

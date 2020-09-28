DROP TABLE IF EXISTS FILME;

CREATE TABLE FILME (
  ID NUMBER AUTO_INCREMENT  PRIMARY KEY,
  TITULO_ORIGINAL VARCHAR(100) NULL,
  TITULO_TRADUZIDO VARCHAR(100) NULL,
  NOME_DIRETOR VARCHAR(100) NULL,
  DATA_LANCAMENTO DATE NULL,
  GENERO VARCHAR (20) NULL,
  FLAG_MAIOR_IDADE varchar(1) NULL,
  VALOR_BILHETERIA NUMBER(18,2) NULL,
  NOTA_IMDB NUMBER (18,2) NULL
);

INSERT INTO FILME (TITULO_ORIGINAL, TITULO_TRADUZIDO, NOME_DIRETOR, DATA_LANCAMENTO, GENERO, FLAG_MAIOR_IDADE, VALOR_BILHETERIA, NOTA_IMDB) VALUES
  ('The Matrix', 'Matrix', 'The Wachowski Brothers', '1999-05-21', 'Sci-fi', 'N', 465343787, 8.7),
  ('The Shawshank Redemption', 'Um Sonho de Liberdade', 'Frank Darabont', '1995-03-17', 'Drama', 'N', 28815245, 9.3),
  ('The Godfather', 'O Poderoso Chefão','Francis Ford Coppola', '1972-09-10', 'Mafia', 'S', 246120974, 9.2),
  ('The Godfather: Part II', 'O Poderoso Chefão II','Francis Ford Coppola', '1975-05-14', 'Mafia', 'S', 48035783, 9.0),
  ('The Dark Knight', 'Batman: O Cavaleiro das Trevas','Christopher Nolan', '2008-07-18', 'Ação', 'N', 1037837499, 9.0),
  ('12 Angry Men', '12 Homens e uma Sentença','Sidney Lumet', '1957-10-10', 'Drama', 'N', 576, 8.9),
  ('Schindler s List', 'A Lista de Schindler','Steven Spielberg', '1994-03-11', 'Biografia', 'N', 322287794, 8.9),
  ('The Lord of the Rings: The Return of the King', 'O Senhor dos Anéis: O Retorno do Rei','Peter Jackson', '2003-12-25', 'Fantasia', 'N', 1141982172, 8.9),
  ('Pulp Fiction', 'Pulp Fiction: Tempo de Violência','Quentin Tarantino', '1995-03-03', 'Crime', 'S', 222831519, 8.9),
  ('Il buono, il brutto, il cattivo', 'Três Homens em Conflito','Sergio Leone', '1968-01-11', 'Velho Oeste', 'N', 25252481, 8.8),
  ('The Lord of the Rings: The Fellowship of the Ring', 'O Senhor dos Anéis: A Sociedade do Anel','Peter Jackson', '2002-01-01', 'Fantasia', 'N', 887832826, 8.8),
  ('Fight Club', 'Clube da Luta','David Fincher', '1999-10-29', 'Drama', 'S', 111571684, 8.8);
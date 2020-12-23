#Para cargar algunos usuarios
INSERT INTO `infordb1`.`user` (`id`, `apellido`, `ciudad`, `email`, `fecha_creacion`, `nombre`, `pais`, `password`, `provincia`) VALUES ('4', 'Landriel', 'Santa Fe', 'landi@gmail.com', '2020-12-13', 'Federico', 'Argentina', '2311428', 'Santa Fe');
INSERT INTO `infordb1`.`user` (`id`, `apellido`, `ciudad`, `email`, `fecha_creacion`, `nombre`, `pais`, `password`, `provincia`) VALUES ('7', 'Romero', 'Sauce', 'rombel@gmail.com', '2020-12-17', 'Belen', 'Argentina', '22loka12', 'Corrientes');
INSERT INTO `infordb1`.`user` (`id`, `apellido`, `ciudad`, `email`, `fecha_creacion`, `nombre`, `pais`, `password`, `provincia`) VALUES ('12', 'Alfonso', 'Posadas', 'geral@gmail.com', '2020-12-22', 'German', 'Argentina', '15ger78', 'Misiones');
INSERT INTO `infordb1`.`user` (`id`, `apellido`, `ciudad`, `email`, `fecha_creacion`, `nombre`, `pais`, `password`, `provincia`) VALUES ('2', 'Babugia', 'San Martin', 'babu@gmail.com', '2020-12-06', 'Florencia', 'Argentina', 'flor_cita', 'Chaco');

#Para cargar algunos post de algunos usuarios

INSERT INTO `infordb1`.`post` (`id`, `contenido`, `descripcion`, `fecha_creacion`, `publicado`, `titulo`, `autor`) VALUES ('3', 'Gozala, que la vida es una mami gozala, mira que este mundo da mil vueltas, ojala y no llegues a mi puerta por que no te abrire.', 'Cancion', '2020-12-18', TRUE, 'Parse', '2');
INSERT INTO `infordb1`.`post` (`id`, `contenido`, `descripcion`, `fecha_creacion`, `publicado`, `titulo`, `autor`) VALUES ('8', 'La gratitud es la memoria del corazón.', 'Frase', '2020-12-15', FALSE, 'Gratitud', '4');
INSERT INTO `infordb1`.`post` (`id`, `contenido`, `descripcion`, `fecha_creacion`, `publicado`, `titulo`, `autor`) VALUES ('12', '— ¿Por qué estás hablando con esas zapatillas? — Porque pone \"converse\"', 'Chiste', '2020-12-20', TRUE, 'Zapas Hablantes', '7');

#Para cargar relaciones entre usuarios y posts

INSERT INTO `infordb1`.`user_posts` (`user_id`, `posts_id`) VALUES ('2', '3');
INSERT INTO `infordb1`.`user_posts` (`user_id`, `posts_id`) VALUES ('4', '8');
INSERT INTO `infordb1`.`user_posts` (`user_id`, `posts_id`) VALUES ('7', '12');

#Para cargar algunos comentarios de algunos usuarios

INSERT INTO `infordb1`.`comentario` (`id`, `autor`, `comentario`, `fecha_creacion`, `post`) VALUES ('14', 'rombel@gmail.com', 'jajaja que gracioso!', '2020-12-21', '12');
INSERT INTO `infordb1`.`comentario` (`id`, `autor`, `comentario`, `fecha_creacion`, `post`) VALUES ('17', 'landi@gmail.com', 'uhh temaikenn reina!', '2020-12-20', '3');

#Para cargar relaciones entre los comentarios y los posts

INSERT INTO `infordb1`.`post_comentarios` (`post_id`, `comentarios_id`) VALUES ('12', '14');
INSERT INTO `infordb1`.`post_comentarios` (`post_id`, `comentarios_id`) VALUES ('3', '17');

CREATE TABLE IF NOT EXISTS topico (
  id BIGINT NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(200) NOT NULL,
  mensaje TEXT NOT NULL,
  fecha_creacion DATETIME NOT NULL,
  status VARCHAR(20) NOT NULL,
  autor VARCHAR(120) NOT NULL,
  curso VARCHAR(120) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT uk_topico_titulo_mensaje UNIQUE (titulo, mensaje(191))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
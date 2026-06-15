
-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS clinica
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE clinica;

-- -------------------------------------------------------
--  Limpiar tablas y vistas si ya existían
-- -------------------------------------------------------
DROP VIEW  IF EXISTS v_ingresos;
DROP TABLE IF EXISTS ingresos;
DROP TABLE IF EXISTS mascotas;

-- -------------------------------------------------------
--  Tabla: mascotas
-- -------------------------------------------------------
CREATE TABLE mascotas (
    id          INT          NOT NULL AUTO_INCREMENT,
    nombre      VARCHAR(100) NOT NULL,
    especie     VARCHAR(50)  NOT NULL,   -- 'perro', 'gato' u otra
    propietario VARCHAR(100) NOT NULL,
    peso        DOUBLE       NOT NULL,
    ingresada   BOOLEAN      NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -------------------------------------------------------
--  Tabla: ingresos
-- -------------------------------------------------------
CREATE TABLE ingresos (
    id          INT          NOT NULL AUTO_INCREMENT,
    id_mascota  INT          NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    coste_base  DOUBLE       NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_mascota FOREIGN KEY (id_mascota)
        REFERENCES mascotas(id)
        ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -------------------------------------------------------
--  Vista: v_ingresos
--  Evita el JOIN en Java — calcula el IVA directamente
-- -------------------------------------------------------
CREATE VIEW v_ingresos AS
SELECT
    c.id,
    m.nombre      AS mascota,
    c.descripcion,
    c.coste_base,
    ROUND(c.coste_base *
        CASE WHEN m.especie IN ('perro', 'gato') THEN 1.10 ELSE 1.21 END
    , 2)          AS total_iva
FROM ingresos c
JOIN mascotas m ON c.id_mascota = m.id;

-- -------------------------------------------------------
--  Datos de prueba — mascotas
--  Incluye las tres casuísticas de IVA:
--    gato/perro → ×1.10  |  otra especie → ×1.21
-- -------------------------------------------------------
INSERT INTO mascotas (nombre, especie, propietario, peso, ingresada) VALUES
('Luna',   'gato',   'María López',  4.2,  TRUE),   -- id=1  ingresada
('Rex',    'perro',  'Carlos Ruiz',  18.5, FALSE),   -- id=2  en casa
('Nemo',   'pez',    'Ana García',   0.3,  TRUE),    -- id=3  ingresada
('Coco',   'perro',  'Luis Martín',  8.7,  FALSE),   -- id=4  en casa
('Pelusa', 'conejo', 'Sara Torres',  2.1,  FALSE),   -- id=5  en casa
('Mia',    'gato',   'Elena Ruiz',   3.8,  FALSE);   -- id=6  en casa

-- -------------------------------------------------------
--  Datos de prueba — ingresos
--  Cubren al menos dos casos de IVA para verificar
-- -------------------------------------------------------
INSERT INTO ingresos (id_mascota, descripcion, coste_base) VALUES
(1, 'Revisión anual',  30.00),   -- Luna (gato)  → 30 × 1.10 = 33,00 €
(3, 'Control de peso', 20.00);   -- Nemo (pez)   → 20 × 1.21 = 24,20 €

-- -------------------------------------------------------
--  Verificación rápida
-- -------------------------------------------------------
SELECT CONCAT('Mascotas cargadas: ', COUNT(*))  AS resultado FROM mascotas;
SELECT CONCAT('Ingresos cargados: ',    COUNT(*))  AS resultado FROM ingresos;
SELECT * FROM v_ingresos;

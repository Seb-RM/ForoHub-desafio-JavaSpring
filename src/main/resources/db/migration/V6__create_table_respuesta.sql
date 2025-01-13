CREATE TABLE respuesta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mensaje TEXT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    solucion BOOLEAN DEFAULT FALSE,
    autor_id INT NOT NULL,
    topico_id INT NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (topico_id) REFERENCES topico(id) ON DELETE CASCADE
);

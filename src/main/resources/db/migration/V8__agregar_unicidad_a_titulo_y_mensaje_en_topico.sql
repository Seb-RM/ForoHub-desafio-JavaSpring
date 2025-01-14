ALTER TABLE topico MODIFY COLUMN mensaje VARCHAR(255);
CREATE UNIQUE INDEX idx_unico_titulo_mensaje ON topico (titulo, mensaje);

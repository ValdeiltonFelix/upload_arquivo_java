# upload_arquivo_java
Upload de arquivo utilizando a linguagem javaEE

Para rodar esses arquivo é preciso ter os seguintes requisitos 

#### Apache tomcat ou algum servidor de aplicação desejado..
#### Postgres instalado
#### Uma aplicacação javaee rodando ou criada

# Tabela 
```
CREATE TABLE public.arquivo
(
  id integer NOT NULL DEFAULT nextval('arquivo_id_seq'::regclass),
  idusuario integer,
  nomearquivo character varying(255),
  blob text,
  tipo character varying(200),
  CONSTRAINT arquivo_pkey PRIMARY KEY (id)
)

  ```

package beans;

import Interface.MensagemEstatus;

public class Arquivos implements MensagemEstatus {
 private int id;
 private int idUsuario;
 private String nomearquivo;
 private String blob;
 private String tipo;
 private int errocod;
 private String  message;
 private int status;

 public int getId() {
	return id;
}
 
public void setId(int id) {
	this.id = id;
}

public int getIdUsuario() {
	return idUsuario;
}
public void setIdUsuario(int idUsuario) {
	this.idUsuario = idUsuario;
}
public String getNomearquivo() {
	return nomearquivo;
}
public void setNomearquivo(String nomearquivo) {
	this.nomearquivo = nomearquivo;
}

public String getBlob() {
	return blob;
}

public void setBlob(String blob) {
	this.blob = blob;
}
@Override
public void setErrocod(int erroCod) {
	this.errocod=erroCod;
	
}
@Override
public int getErrocod() {
	
	return errocod;
}
@Override
public void setMessage(String message) {
	 this.message=message;
	
}
@Override
public String getMessage() {
	// TODO Auto-generated method stub
	return  message;
}
@Override
public void setStatus(int status) {
	// TODO Auto-generated method stub
	this.status=status;
}
@Override
public int getStatus() {
	// TODO Auto-generated method stub
	return status;
}

public String getTipo() {
	return tipo;
}

public void setTipo(String tipo) {
	this.tipo = tipo;
}
	
	
}

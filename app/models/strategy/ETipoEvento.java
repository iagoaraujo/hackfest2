package models.strategy;

public enum ETipoEvento {

	NORMAL("evento normal"),
	PRIORIDADE_EXPERIENTES("evento prioritario para experientes");
	
    private final String nome;

    private ETipoEvento(String nome) {
    	this.nome = nome;
    }

    public String getNome() {
    	return nome;
    }
    
    public static ETipoEvento getTipoStrategy(String value) {
    	for (ETipoEvento tipo: ETipoEvento.values()) {
    		if (tipo.getNome().equals(value)) {
    			return tipo;
    		}
    	}
    	return null;
    }
}

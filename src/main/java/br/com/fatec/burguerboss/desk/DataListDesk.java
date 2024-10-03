package br.com.fatec.burguerboss.desk;

public record DataListDesk(
        int id,
        boolean filled
) {
    public DataListDesk(Desk desk){
        this(desk.getId() , desk.isFilled());
    }
}

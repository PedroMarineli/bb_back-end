package br.com.fatec.burguerboss.desk;

public record DataListDesk(
        Integer id,
        boolean filled
) {
    public DataListDesk(Desk desk){
        this(desk.getId() , desk.isFilled());
    }
}

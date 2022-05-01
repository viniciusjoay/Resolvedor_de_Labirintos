public abstract class PilhaEstatica{
    protected int top;
    protected int[] elements;

    public PilhaEstatica(int capacidade) {
        if(capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser maior que zero.");
        }
        top = -1;
        elements = new int[capacidade];
    }

    public int pop() {
        if(isEmpty()) {
            throw new IllegalStateException("A pilha está vazia.");
        }
        int componente = elements[top];
        elements[top] = 0;
        top = top - 1;
        return componente;
    }

    public int getTamanho() {
        return top + 1;
    }

    public int getCapacidade() {
        return elements.length;
    }

    public boolean isEmpty() {
        return top < 0;
    }
}
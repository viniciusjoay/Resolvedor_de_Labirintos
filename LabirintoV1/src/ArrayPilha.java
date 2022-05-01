import java.util.Arrays;
public class ArrayPilha extends PilhaEstatica {
    private float taxaCrascimento;
    
    public void push(int elementos) {
        if(top >= getCapacidade() - 1) {
            int newCapacidade = (int)(getCapacidade() * (taxaCrascimento + 1));
            newCapacidade = Math.max(getCapacidade() + 1, newCapacidade);
            var newArray = Arrays.copyOf(elements, newCapacidade);
            elements = newArray;
        }

        top = top + 1;
        elements[top] = elementos;
    }

    public ArrayPilha(int capacidade, float taxaCrascimento) {
        super(capacidade);
        this.taxaCrascimento = taxaCrascimento;
    }

    public int ultimaPos() {
        if(top >= 0) {
            return elements[top];
        }
        return 0;
    }

    public String toString() {
        String borda = "[";
        if(getTamanho() > 0) {
            borda += this.elements[0];
        }
        for (int i = 1; i < getTamanho(); i++) {
            borda += ", " + this.elements[i];
        }
        borda += "]";
        return borda;
    }
}

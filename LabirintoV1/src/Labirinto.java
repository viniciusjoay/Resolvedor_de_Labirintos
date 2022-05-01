import java.util.Arrays;

public class Labirinto {
    private final String [][] labirinto = { //Matriz do labirinto | . = Parede |  = Caminho | A = Ponto Inicial | B = Saída
            {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
            {".", " ", " ", " ", " ", " ", "A", ".", ".", ".", ".", "B", "."},
            {".", " ", ".", " ", ".", ".", " ", ".", ".", " ", " ", " ", "."},
            {".", " ", ".", " ", ".", ".", " ", ".", " ", " ", ".", ".", "."},
            {".", " ", " ", " ", " ", " ", " ", " ", " ", ".", ".", ".", "."},
            {".", ".", ".", ".", " ", ".", ".", ".", ".", " ", ".", ".", "."},
            {".", ".", " ", " ", " ", ".", ".", ".", " ", " ", ".", ".", "."},
            {".", ".", " ", ".", " ", " ", " ", " ", ".", " ", " ", " ", "."},
            {".", " ", " ", ".", ".", ".", " ", ".", ".", ".", ".", " ", "."},
            {".", " ", ".", ".", ".", ".", " ", ".", ".", ".", ".", " ", "."},
            {".", " ", " ", ".", " ", " ", " ", " ", " ", ".", ".", " ", "."},
            {".", " ", ".", ".", " ", ".", " ", ".", " ", " ", " ", " ", "."},
            {".", ".", ".", " ", " ", ".", " ", ".", ".", ".", " ", ".", "."},
            {".", " ", " ", " ", ".", ".", " ", " ", ".", ".", " ", " ", "."},
            {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."},
    };

    //Cria novas pilhas passando tamanho e taxa de crescimento
    ArrayPilha ultimaPos = new ArrayPilha(1,0);
    ArrayPilha posY = new ArrayPilha(1,0);
    ArrayPilha posZ = new ArrayPilha(1,0);

    public void setaInicio() { //Define o ponto de partida do labirinto (Deve ser igual a posição do ponto A)
        posY.push(1); //Linha inicial
        posZ.push(6); // Coluna inicial
        ultimaPos.push(0);
    }

    //Função principal, verifica o local inicial definido pelo usuário e até que seja encontrada uma saída percorre o labirinto
    public void percorreLabirinto() {
        setaInicio();
        while (!achaSaida()) {
            marcaCaminho();
            System.out.println("\n");
            imprimeLabirinto();
            if ((labirinto[posY.ultimaPos()][posZ.ultimaPos() - 1] == " ") || (labirinto[posY.ultimaPos()][posZ.ultimaPos() - 1] == "B")) {
                posZ.push(posZ.ultimaPos() - 1);
                ultimaPos.push(0);
            }else
                if ((labirinto[posY.ultimaPos() + 1][posZ.ultimaPos()] == " ") || (labirinto[posY.ultimaPos() + 1][posZ.ultimaPos()] == "B")) {
                    posY.push(posY.ultimaPos() + 1);
                    ultimaPos.push(1);
                }else
                    if ((labirinto[posY.ultimaPos()][posZ.ultimaPos() + 1] == " ") || (labirinto[posY.ultimaPos()][posZ.ultimaPos() + 1] == "B")) {
                        posZ.push(posZ.ultimaPos() + 1);
                        ultimaPos.push(2);
                    }else
                        if ((labirinto[posY.ultimaPos() - 1][posZ.ultimaPos()] == " ") || (labirinto[posY.ultimaPos() - 1][posZ.ultimaPos()] == "B")) {
                            posY.push(posY.ultimaPos() - 1);
                            ultimaPos.push(3);
                        }else{
                            switch(ultimaPos.ultimaPos()) {
                                case 0:
                                    posZ.pop();
                                    ultimaPos.pop();
                                    break;
                                case 1:
                                    ultimaPos.pop();
                                    posY.pop();
                                    break;
                                case 2:
                                    ultimaPos.pop();
                                    posZ.pop();
                                    break;
                                case 3:
                                    posY.pop();
                                    ultimaPos.pop();
                                    break;
                            }
                        }
        }
    }
    
    public void marcaCaminho() { //Marca os caminhos já passados com um *
        if (labirinto[posY.ultimaPos()][posZ.ultimaPos()] == "A")
                labirinto[posY.ultimaPos()][posZ.ultimaPos()] = "A";
                else if (labirinto[posY.ultimaPos()][posZ.ultimaPos()] == "B")
                        labirinto[posY.ultimaPos()][posZ.ultimaPos()] = "B";
                        else{
                            labirinto[posY.ultimaPos()][posZ.ultimaPos()] = "*";
                        }
    }

    public boolean achaSaida() { //Verifica se a próxima posição é uma saída
        if (labirinto[posY.ultimaPos()][posZ.ultimaPos()] == "B") {
            System.out.println("\nVoce saiu do labirinto!\n");
            return true;
        }
        else if (posY.isEmpty() || posZ.isEmpty()) { //Verifica se acabaram as possibilidades de caminhos
            System.out.println("\nNao ha saidas neste labirinto, voce esta preso para sempre!\n");
            return true;
        }
        return false;
    }

    public void imprimeLabirinto() { //Imprime a matriz do labirinto
        for (String[] strings : labirinto) {
            String matrizLabirinto = Arrays.toString(strings);
            matrizLabirinto = matrizLabirinto.replace(",", " "); // Substitui , por espaços para melhor vizualização
            System.out.println(matrizLabirinto);
        }
    }
}


package batalha_naval;

import java.util.Random;
import java.util.Scanner;


public class Batalha_naval {
    static Scanner ler = new Scanner(System.in);
    static Random ale = new Random();

    public static void main(String[] args){
        int player = 1;
        char[][] mapaAmigo = new char[10][10];
        char[][] mapaInimigo = new char[10][10];
        marGe(mapaAmigo, mapaInimigo);
        int op = 0;
        boolean preset = false, rep;
        for (;op!=1 && op!=2; ) {
            System.out.println("1 - Player x Computador");
            System.out.println("2 - Player x Player");
            System.out.println("Opção desejada: ");
            op = ler.nextInt();
            if (op==1) {
                System.out.println("Seu mapa: ");
                mapaGe(mapaAmigo);
                alocação(mapaAmigo, preset);
                System.out.println("Mapa do Bot: ");
                mapaGe(mapaInimigo);
                alocação(mapaInimigo, preset=true);
                System.out.println("Bot alocou os barcos");

                boolean vitoria = false;
                for(;!vitoria;){
                    preset = false;
                    rep = true;
                    for(; rep && !vitoria;){
                        System.out.println("Player atacando: ");
                        mapaGe(mapaInimigo);
                        rep = tiro(mapaInimigo, preset);

                    }
                    if(!vitoria){
                        rep = true;
                        preset = true;
                        for(;rep==true && vitoria==false;){
                            System.out.println("Bot atacando: ");
                            mapaGe(mapaAmigo);
                            System.out.println("O BOT está jogando...");
                            rep = tiro(mapaAmigo, preset);
                            player = 2;
                            vitoria = vitoria(mapaAmigo);
                        }
                    }
                }
                vitoriaBot(player, preset);
            } else if (op==2) {
                System.out.println("Player 1: ");
                mapaGe(mapaAmigo);
                alocação(mapaAmigo, preset);
                System.out.println("Player 2: ");
                mapaGe(mapaInimigo);
                alocação(mapaInimigo, preset);

                boolean vitoria = false;
                for(;!vitoria;) {
                    rep = true;
                    for (;rep==true && vitoria==false;){
                        System.out.println("Player 1: ");
                        mapaGe(mapaInimigo);
                        rep = tiro(mapaInimigo, preset);
                        player =1;
                        vitoria = vitoria(mapaInimigo);
                    }
                    if (!vitoria){
                        rep = true;
                        for (;rep==true && vitoria==false;){
                            System.out.println("Player 2: ");
                            mapaGe(mapaAmigo);
                            rep = tiro(mapaAmigo, preset);
                            player =2;
                            vitoria = vitoria(mapaAmigo);
                        }
                    }
                }
                vitoriaBot(player, preset);
            } else {
                System.out.println("Opção errada");
            }
        }
    }
    public static void vitoriaBot(int player, boolean preset){
        if (preset == false){
            System.out.println("Parabéns player 1 você ganhou!!!");
        }
        else
            System.out.println("Parabéns player 2 você ganhou!!!");
    }
    public static boolean vitoria(char[][] mapa){
        int i=0;
        for(int l=0; l<mapa.length; l++){
            for(int c=0; c<mapa[l].length; c++){
                if(mapa[l][c] == 'B')
                    i++;
            }
        }
        if(i>0)
            return false;
        else
            return true;
    }
    public static boolean tiro(char[][] mar, boolean boot){
        int x, y;
        boolean denovo = false;
        x=-1;
        y=-1;
        if(boot==false){
            for(;y<0 || y>9;){
                System.out.print("X - Digite em qual linha você quer atirar:  ");
                y = ler.nextInt();
            }
            for(;x<0 || x>9;){
                System.out.print("Y - Digite em qual coluna você quer atirar:  ");
                x = ler.nextInt();
            }
            if(mar[y][x] == 'B'){
                mar[y][x] = 'b';
                denovo = true;
                System.out.println("Você acertou um barco inimigo!!! Você poderá atirar denovo: ");
            }
            else if(mar[y][x] == 'A'){
                mar[y][x] = 'a';
                System.out.println("Você não acertou um barco inimigo! É a vez do adversário: ");
            }
            else{
                System.out.println("Você já atacou esse lugar! Tente outro: ");
                denovo = true;
            }
        }
        else{
            x = ale.nextInt(0, 10);
            y = ale.nextInt(0, 10);
            if(mar[y][x] == 'B'){
                mar[y][x] = 'b';
                denovo = true;
                System.out.println("O bot acertou um barco seu! Ele irá jogar novamente: ");
            }
            else if(mar[y][x] == 'A'){
                mar[y][x] = 'a';
                System.out.println("O bot errou seus barcos! É a sua evz de jogar: ");
            }
            else{
                denovo = true;
            }
        }
        return denovo;
    }
    public static void alocação(char[][] mapa, boolean preset) {
        boolean corpo;
        boolean rep;
        int[] vet = {4, 3, 2, 1};
        int posiX, posiY;
        char direcao;
        int opUm = 0;
        char y = ' ';
        for (; opUm!=1 && opUm!=2 && preset==false; ) {
            System.out.println("1 - Alocação manual dos barcos");
            System.out.println("2 - Alocação Automática dos barcos");
            System.out.println("Opção desejada: ");
            opUm = ler.nextInt();
        }
        switch (opUm){
            case 1->{
                for (int i=vet.length-1;i>=0;i--) {
                    for (int j=1; j<=vet[i]; j++) {
                        rep = false;
                        corpo = false;
                        for (;corpo == false;) {
                            posiX = -1;
                            posiY = -1;
                            direcao = ' ';
                            if (rep && !preset)
                                System.out.println("Não é possivel alocar o barco aqui");
                            for (; direcao != 'V' && direcao != 'H' && !preset && i > 0; ) {
                                System.out.print("Como você quer alocar seu barco \nH - horizontal: \nV - vertical: \nDo " + j + "º barco de " + (i + 1) + " espaços: ");
                                direcao = ler.next().toUpperCase().charAt(0);
                            }
                            if (i == 0)
                                direcao = 'V';
                            if (!preset)
                                System.out.println("Como você deseja alocar o " + j + "º barco de " + (i + 1) + " espaços: ");
                            if (!preset) {
                                for (; posiX<0 || posiX>9; ) {
                                    System.out.print("X - Digite em qual linha você dejesa alocar o barco: ");
                                    posiX = ler.nextInt();
                                }
                                for (; posiY<0 || posiY>9; ) {
                                    System.out.print("Y - Digite em qual coluna que você dejesa alocar o barco: ");
                                    posiY = ler.nextInt();
                                }
                            }
                            switch (direcao) {
                                case 'H' -> {
                                    if (posiY+i <= 9) {
                                        corpo = true;
                                        for (int t = posiY;t<=(posiY + i); t++) {
                                            if (mapa[t][posiX] != 'A' && mapa[t][posiX] != 'a') {
                                                corpo = false;
                                                rep = true;
                                            }
                                        }
                                        if (corpo) {
                                            for (int l = posiY; l <= (posiY + i); l++)
                                                mapa[l][posiX] = 'B';
                                        }
                                    } else {
                                        rep = true;
                                    }
                                }
                                case 'V' -> {
                                    if (posiX + i <= 9) {
                                        corpo = true;
                                        for (int t = posiX; t <= (posiX + i); t++) {
                                            if (mapa[posiY][t] != 'A' && mapa[posiY][t] != 'a') {
                                                corpo = false;
                                                rep = true;
                                            }
                                        }
                                        if (corpo) {
                                            for (int c = posiX; c <= (posiX + i); c++)
                                                mapa[posiY][c] = 'B';
                                        }
                                    } else {
                                        rep = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            case 2->{
                for (int i = vet.length - 1; i >= 0; i--) {
                    for (int j = 1; j <= vet[i]; j++) {
                        rep = false;
                        corpo = false;
                        for (; corpo == false; ) {
                            char tipoAlo = ' ';
                            posiX = -1;
                            posiY = -1;
                            if (preset == false){
                                posiX = ale.nextInt(0,10);
                                posiY = ale.nextInt(0,10);
                                int AloAle = ale.nextInt(0,2);
                                if (AloAle==1){
                                    tipoAlo = 'V';
                                } else if (AloAle==2) {
                                    tipoAlo= 'H';
                                }
                                switch (tipoAlo) {
                                    case 'H' -> {
                                        if (posiY + 1 <= 9) {
                                            corpo = true;
                                            for (int t = posiY; t <= (posiY + i); t++) {
                                                if (mapa[t][posiX] != 'A' && mapa[t][posiX] != 'a') {
                                                    corpo = false;
                                                    rep = true;
                                                }
                                            }
                                            if (corpo == true) {
                                                for (int l = posiY; l <= (posiY + i); l++)
                                                    mapa[l][posiX] = 'B';
                                            }
                                        } else {
                                            rep = true;
                                        }
                                    }
                                    case 'V' -> {
                                        if (posiX + i <= 9) {
                                            corpo = true;
                                            for (int t = posiX; t <= (posiX + i); t++) {
                                                if (mapa[posiY][t] != 'A' && mapa[posiY][t] != 'a') {
                                                    corpo = false;
                                                    rep = true;
                                                }
                                            }
                                            if (corpo == true) {
                                                for (int c = posiX; c <= (posiX + i); c++)
                                                    mapa[posiY][c] = 'B';
                                            }
                                        } else {
                                            rep = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }
    public static void mapaGe(char[][] mapa) {
        String map = "";
        map += String.format("    |0||1||2||3||4||5||6||7||8||9|\n");
        for (int i = 0; i< mapa.length; i++) {
            map += String.format("|%d| ", i);
            for (int j = 0; j< mapa.length; j++) {
                if(mapa[i][j] == 'A' || mapa[i][j] == 'B')
                    map += String.format("[ ]");
                else if(mapa[i][j]=='a'){
                    map += String.format("[O]");
                }
                else
                    map += String.format("[X]");
            }
            map += String.format("\n");
        }
        System.out.print(map);
    }
    public static void marGe ( char[][] marAmi, char[][] marIni){
        for (int l = 0; l < marAmi.length; l++) {
            for (int c = 0; c < marAmi[l].length; c++) {
                marAmi[l][c] = 'A';
            }
        }
        for (int l = 0; l < marIni.length; l++) {
            for (int c = 0; c < marIni[l].length; c++) {
                marIni[l][c] = 'A';
            }
        }
    }
}


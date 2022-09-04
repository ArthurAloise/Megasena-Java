package megasena;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MegaSenaJogo {
	
	public static void main(String[] args) throws Exception {
		 Scanner leitor = new Scanner(System.in);
	        System.out.print("Quantos jogos deseja criar:   ");
	        int qtdJogos = leitor.nextInt();
	        
	        System.out.print("Quantos n�meros em cada jogo: ");
	        int qtdNumeros = leitor.nextInt();
	        
	        System.out.print("Menor valor do m�mero:        ");
	        int min = leitor.nextInt();
	        
	        System.out.print("Maior valor do n�mero:        ");
	        int max = leitor.nextInt();
	        
	        String textoJogo = gerarGames(qtdJogos, qtdNumeros, min, max);
	        textToArq(textoJogo, "Megasena.txt");
	        
	        leitor.close();
    }
	
	public static String gerarGames(int qtdJogos, int qtdNumeros, int min, int max){
		String saida = "";
		for(int i = 0; i < qtdJogos; i++) {
			int [] vetorjogo = gerarjogo(qtdNumeros, min, max);
			String stringJogo = vetorToString(vetorjogo);
			saida = saida + stringJogo +"\n";
		}
		return saida;
	}

	public static int numaleatorio(int min, int max) {
		if (max < min) {
			throw new IllegalArgumentException("Argumento inv�lido");
		}
		return new Random().nextInt(max - min + 1) + min;
	}

	public static boolean existeRepetido(int[] vetor, int elemento) {
		for (int i = 0; i < vetor.length; i++) {
			if (vetor[i] == elemento) {
				return true;
			}
		}
		return false;
	}

	public static int[] gerarjogo(int numeros, int min, int max) {
		var jogo = new int[numeros];
		int numerosGerados = 0;
		while (numerosGerados < numeros) {
			var numero = numaleatorio(min, max);
			if (!existeRepetido(jogo, numero)) {
				jogo[numerosGerados++] = numero;
			}
		}
		Arrays.sort(jogo);
		return jogo;
	}

	public static String vetorToString(int[] vetor) {
		var saida = "[";
		for (int i = 0; i < vetor.length - 1; i++) {
			saida += vetor[i] + ",";
		}
		return saida + vetor[vetor.length - 1] + "]";
	}
	
	public static void textToArq(String texto, String arquivo) {
		System.out.println(texto);
		try {
			//Tente executar
			FileWriter file = new FileWriter(arquivo);
			BufferedWriter escritor = new BufferedWriter(file);
			escritor.append(texto);
			file.close();
		}catch (Exception ex){
			//Se der erro, mostre qual �.
			System.err.println(ex.getMessage());
		}
	}
}

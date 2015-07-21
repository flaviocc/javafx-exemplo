package formularioLogin;

import java.util.ArrayList;
import java.util.List;

public class Vitrine {

	private static List<Produto> produtos = new ArrayList<>();

	public void addProdutos(Produto... ps) {
		for (Produto p : ps) {
			produtos.add(p);
		}
	}

	public static List<Produto> getProdutos() {
		return produtos;
	}

	public static void setProdutos(List<Produto> produtos) {
		Vitrine.produtos = produtos;
	}

}

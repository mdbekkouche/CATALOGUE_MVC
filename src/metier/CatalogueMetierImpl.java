package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogueMetierImpl implements ICatalogueMetier {

	@Override
	public void addProduit(Produit p) {
		Connection conn=SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into PRODUITS(REF_PRODUIT,DESIGNATIONS,PRIX,QUANTITE) values (?,?,?,?)");
		    ps.setString(1, p.getReference());
		    ps.setString(2, p.getDesignation());
		    ps.setDouble(3, p.getPrix());
		    ps.setInt(4, p.getQuantite());
		    ps.executeUpdate();
		    ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<Produit> listProduit() {
		List<Produit> prods=new ArrayList<Produit>();
		Connection conn=SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM PRODUITS");
		    ResultSet rs = ps.executeQuery();
		    while(rs.next()) {
		    	Produit p = new Produit();
		    	p.setReference(rs.getString("REF_PRODUIT"));
		    	p.setDesignation(rs.getString("DESIGNATIONS"));
		    	p.setPrix(rs.getDouble("PRIX"));
		    	p.setQuantite(rs.getInt("QUANTITE"));
		    	prods.add(p);
		    }
		    ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prods;
	}

	@Override
	public List<Produit> produitParMC(String mc) {
		List<Produit> prods=new ArrayList<Produit>();
		Connection conn=SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM PRODUITS where DESIGNATIONS like ?");
			ps.setString(1, "%" + mc + "%");
		    ResultSet rs = ps.executeQuery();
		    while(rs.next()) {
		    	Produit p = new Produit();
		    	p.setReference(rs.getString("REF_PRODUIT"));
		    	p.setDesignation(rs.getString("DESIGNATIONS"));
		    	p.setPrix(rs.getDouble("PRIX"));
		    	p.setQuantite(rs.getInt("QUANTITE"));
		    	prods.add(p);
		    }
		    ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prods;
	}

	@Override
	public Produit gatProduit(String ref) {
		Produit p = null;
		List<Produit> prods=new ArrayList<Produit>();
		Connection conn=SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM PRODUITS where REF_PRODUIT = ?");
			ps.setString(1, ref);
		    ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				p = new Produit();
				p.setReference(rs.getString("REF_PRODUIT"));
				p.setDesignation(rs.getString("DESIGNATIONS"));
				p.setPrix(rs.getDouble("PRIX"));
				p.setQuantite(rs.getInt("QUANTITE"));
			}
		    ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (p == null) throw new RuntimeException("Produit " + ref + " est introuvable");
		return p;
	}

	@Override
	public void updateProduit(Produit p) {
		Connection conn=SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("update PRODUITS set DESIGNATIONS = ?, PRIX = ?, QUANTITE = ? where REF_PRODUIT = ?");
		    ps.setString(1, p.getDesignation());
		    ps.setDouble(2, p.getPrix());
		    ps.setInt(3, p.getQuantite());
		    ps.setString(4, p.getReference());
		    ps.executeUpdate();
		    ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	@Override
	public void deleteProduit(String ref) {
		Connection conn=SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("delete FROM PRODUITS where REF_PRODUIT = ?");
		    ps.setString(1, ref);
		    ps.executeUpdate();
		    ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}

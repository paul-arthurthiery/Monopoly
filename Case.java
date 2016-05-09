package monopoly;

public abstract class Case
{
	protected String adresse;
	protected int numero;

	
	public Case( String adresse, int numero)
	{
		this.adresse = adresse;
		this.numero = numero;
	}
	
	public String getAdresse()
	{
		return( this.adresse );
	}
	
	public int getNumero()
	{
		return( this.numero );
	}
	

	
}

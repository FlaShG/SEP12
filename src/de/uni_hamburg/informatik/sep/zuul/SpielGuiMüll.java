package de.uni_hamburg.informatik.sep.zuul;

import de.uni_hamburg.informatik.sep.zuul.spiel.Raumbilderzeuger;
import de.uni_hamburg.informatik.sep.zuul.spiel.SpielKontext;
import de.uni_hamburg.informatik.sep.zuul.spiel.TickListener;

public class SpielGuiMüll
{

	
	@Override
	public void schreibeNL(String nachricht)
	{
		schreibe(nachricht);
		_ap.getAnzeigeArea().append("\n");
	}

	@Override
	public void schreibe(String nachricht)
	{

		anzeige.append(nachricht);

	}

	@Override
	public void beendeSpiel()
	{

		UIsetEnabled(false);
	}

	/**
	 * 
	 */
	private void UIsetEnabled(boolean value)
	{
		_ep.getEingabeZeile().setEnabled(value);
		_ep.getEnterButton().setEnabled(value);

		_bp.getSouthButton().setEnabled(value);
		_bp.getNorthButton().setEnabled(value);
		_bp.getWestButton().setEnabled(value);
		_bp.getEastButton().setEnabled(value);
		_bp.getGibButton().setEnabled(value);
		_bp.getEssenButton().setEnabled(value);
		_bp.getEssenBodenButton().setEnabled(value);
		_bp.getNehmenButton().setEnabled(value);
		_bp.getHelpButton().setEnabled(value);
		_bp.getQuitButton().setEnabled(value);
		_bp.getFuettereButton().setEnabled(value);
		_bp.getAblegenButton().setEnabled(value);
		_bp.getInventarButton().setEnabled(value);
	}

	public void schliesseFenster()
	{
		_hf.hide();
	}

	@Override
	protected void verarbeiteEingabe(String eingabezeile)
	{
		schreibeNL("> " + eingabezeile);
		super.verarbeiteEingabe(eingabezeile);
	}

	@Override
	public void spielen(String level)
	{
		UIsetEnabled(true);

		super.spielen(level);

		_kontext.addTickListener(new TickListener()
		{

			@Override
			public boolean tick(SpielKontext kontext, boolean hasRoomChanged)
			{
				zeichneBild();
				return true;
			}
		});
		zeichneBild();
	}

	private void zeichneBild()
	{
		Raumbilderzeuger raumbilderzeuger = new Raumbilderzeuger(_kontext);
		_bp.setRaumanzeige(raumbilderzeuger.getRaumansicht());
	}
}

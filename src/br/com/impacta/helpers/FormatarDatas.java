package br.com.impacta.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatarDatas {
	public static Date formatar(String data, String formato) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
		return simpleDateFormat.parse(data);
	}
}

package co.gov.antioquia;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CadastralUpdateReportGenerator {

	public void getDirectoryPath() {

	}

	public ArrayList<ArrayList<String>> getInformation(DBConnector dbConnector, List<String> baseInformation) {
		ArrayList<ArrayList<String>> dataArrayList = new ArrayList<ArrayList<String>>();
		dataArrayList.add(this.getInformationTable1(dbConnector, baseInformation));
		dataArrayList.add(this.getInformationTable2(dbConnector, baseInformation));
		dataArrayList.add(this.getInformationTable3(dbConnector, baseInformation));
		dataArrayList.add(this.getInformationTable4(dbConnector, baseInformation));
		dataArrayList.add(this.getInformationTable5(dbConnector, baseInformation));
		dataArrayList.add(this.getInformationTable6(dbConnector, baseInformation));
		dataArrayList.add(this.getInformationTable7(dbConnector, baseInformation));
		dataArrayList.add(this.getInformationTable8(dbConnector, baseInformation));
		return dataArrayList;
	}

	public void buildReport(DBConnector dbConnector, String RESO_VIGENCIA, String RESO_RESOLUC, String RESO_MPIO,
			String RESO_SECTOR) {
		List<String> baseInformation = new ArrayList<String>();
		ArrayList<ArrayList<String>> allData = null;
		int allDataSize = 0;
		baseInformation.add(RESO_VIGENCIA);
		baseInformation.add(RESO_RESOLUC);
		baseInformation.add(RESO_MPIO);
		baseInformation.add(RESO_SECTOR);
		baseInformation.add(this.getTotalNumberOfReg(dbConnector));
		baseInformation.add(this.getNumberOfReg(dbConnector));
		baseInformation.add(this.getAreaOfField(dbConnector));
		baseInformation.add(this.getAreaOfBuild(dbConnector));
		baseInformation.add(this.getSumOfPoints(dbConnector));
		allData = getInformation(dbConnector, baseInformation);
		allDataSize = allData.size();
		// Get the file reference
		Path path = Paths.get("/home/mrwolf/Documents/actualizacionCatastral.txt");

		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			for (int i = 1; i <= allDataSize; i++) {
				for (int j = 1; j <= allData.get(i).size(); j++)
					writer.write(allData.get(i).get(j));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<String> getInformationTable1(DBConnector dbConnector, List<String> baseInformation) { // Resoluci
		ArrayList<String> tableData = new ArrayList<String>();
		String RESO_ID_REG = "1";
		String RESO_VIGENCIA = baseInformation.get(0);
		System.out.printf("Vigencia resolución: " + RESO_VIGENCIA + "%n");
		String RESO_TIPO_RESO = "184";
		String RESO_RESOLUC = baseInformation.get(1);
		System.out.printf("# Resolución: " + RESO_RESOLUC + "%n");
		String RESO_MPIO = baseInformation.get(2);
		System.out.printf("MUNICIPIO: " + RESO_MPIO + "%n");
		String RESO_SECTOR = baseInformation.get(3);
		System.out.printf("SECTOR: " + RESO_SECTOR + "%n");
		String RESO_NRO_REG = baseInformation.get(4);
		String RESO_AREA_TERRE = "000000000000";
		String RESO_AREA_CONS = baseInformation.get(7);
		String RESO_SUMA_PTOS = baseInformation.get(8);
		String RESO_PARCIAL_TOTAL = "1";
		String basicLine = RESO_ID_REG + RESO_VIGENCIA + RESO_TIPO_RESO + RESO_RESOLUC + RESO_MPIO + RESO_SECTOR
				+ RESO_NRO_REG + RESO_AREA_TERRE + RESO_AREA_CONS + RESO_SUMA_PTOS + RESO_PARCIAL_TOTAL;
		tableData.add(basicLine);
		return tableData;
	}

	public ArrayList<String> getInformationTable2(DBConnector dbConnector, List<String> baseInformation) { // Mutacion
		ArrayList<String> tableData = new ArrayList<String>();
		ResultSet resultSet = null;
		ResultSet resultSetTemp = null;
		String MUTA_ID_REG = "2";// (1)
		String MUTA_VIGENCIA = baseInformation.get(0); // (4)
		String MUTA_TIPO_RESO = "184";// (3)
		String MUTA_RESOLUC = baseInformation.get(1);// (7)
		String MUTA_NRO_FICHA = "";
		String MUTA_NRO_REG = baseInformation.get(5);// (5)
		String MUTA_MPIO = baseInformation.get(2);// (3)
		String MUTA_SECTOR = baseInformation.get(3);// (1)
		String MUTA_CORREG = "000"; // (3)Number
		String MUTA_BARRIO = "";
		String MUTA_MANZ_VERE = "";
		String MUTA_PREDIO = "";
		String MUTA_EDIFICIO = "";
		String MUTA_UND_PRED = "";
		String MUTA_AREA_TERR = "000000000000";
		String MUTA_DIRECCION = "XXXXXXXXXXXXXXX XXXXXXXXXXXXXX XXXXXXXXXX XXXXXXX";// (49)Varchar2
		String MUTA_CARACTERIST = "0";// (1)Number
		String MUTA_CLASIF_SUELO = "";
		String MUTA_COEFIC_PREDIO = "000000000";// (9,6)Number
		String MUTA_COEFIC_EDIFIC = "000000000";// (9,6)Number
		String MUTA_MPIO_ANT = baseInformation.get(2);// (3)
		String MUTA_SECTOR_ANT = baseInformation.get(3);// (1);
		String MUTA_CORREG_ANT = "000";
		String MUTA_BARRIO_ANT = "";
		String MUTA_MANZ_VERED_ANT = "";
		String MUTA_PREDIO_ANT = "";
		String MUTA_EDIFICIO_ANT = "";
		String MUTA_UND_PRED_ANT = "";
		String MUTA_CLASIF_SUELO_ANT = "";
		String basicLine = "";
		// dbConnector.execQuery("SELECT * FROM public.col_derecho", dbConnector.conn);
		try {
			resultSet = dbConnector.execQuery("SELECT * FROM public.predio", dbConnector.conn);
			while (resultSet.next()) {
				MUTA_NRO_FICHA = resultSet.getString("nupre");// (9)
				resultSetTemp = dbConnector.execQuery(
						"SELECT barrio FROM public.predio_ficha WHERE t_id=" + resultSet.getString("t_id"),
						dbConnector.conn);
				resultSetTemp.next();
				MUTA_BARRIO = resultSetTemp.getString("barrio");
				System.out.printf("Barrio: " + MUTA_BARRIO + "%n");
				resultSetTemp = dbConnector.execQuery(
						"SELECT manzana_vereda FROM public.predio_ficha WHERE t_id=" + resultSet.getString("t_id"),
						dbConnector.conn);
				resultSetTemp.next();
				MUTA_MANZ_VERE = resultSetTemp.getString("manzana_vereda");
				System.out.printf("Manzana o vereda " + MUTA_MANZ_VERE + "%n");
				resultSetTemp = dbConnector.execQuery(
						"SELECT numero_predial_anterior FROM public.predio WHERE t_id=" + resultSet.getString("t_id"),
						dbConnector.conn);
				resultSetTemp.next();
				MUTA_PREDIO = resultSetTemp.getString("numero_predial_anterior");
				System.out.printf("Predio: " + MUTA_PREDIO + "%n");
				resultSetTemp = dbConnector.execQuery(
						"SELECT edificio FROM public.predio_ficha WHERE t_id=" + resultSet.getString("t_id"),
						dbConnector.conn);
				resultSetTemp.next();
				MUTA_EDIFICIO = resultSetTemp.getString("edificio");
				System.out.printf("Edificio: " + MUTA_EDIFICIO + "%n");
				resultSetTemp = dbConnector.execQuery(
						"SELECT unidad FROM public.predio_ficha WHERE t_id=" + resultSet.getString("t_id"),
						dbConnector.conn);
				resultSetTemp.next();
				MUTA_UND_PRED = resultSetTemp.getString("unidad");
				System.out.printf("Unidad: " + MUTA_UND_PRED + "%n");
				resultSetTemp = dbConnector.execQuery(
						"SELECT categoria_suelo_pot FROM public.predio_ficha WHERE t_id=" + resultSet.getString("t_id"),
						dbConnector.conn);
				resultSetTemp.next();
				MUTA_CLASIF_SUELO = resultSetTemp.getString("categoria_suelo_pot");
				System.out.printf("Clasificación suelo: " + MUTA_CLASIF_SUELO + "%n");
				resultSetTemp = dbConnector.execQuery(
						"SELECT barrio FROM public.predio_ficha WHERE t_id=" + resultSet.getString("t_id"),
						dbConnector.conn);
				resultSetTemp.next();
				MUTA_BARRIO_ANT = resultSetTemp.getString("barrio");
				resultSetTemp = dbConnector.execQuery(
						"SELECT manzana_vereda FROM public.predio_ficha WHERE t_id=" + resultSet.getString("t_id"),
						dbConnector.conn);
				resultSetTemp.next();
				MUTA_MANZ_VERED_ANT = resultSetTemp.getString("manzana_vereda");
				resultSetTemp = dbConnector.execQuery(
						"SELECT numero_predial_anterior FROM public.predio WHERE t_id=" + resultSet.getString("t_id"),
						dbConnector.conn);
				resultSetTemp.next();
				MUTA_PREDIO_ANT = resultSetTemp.getString("numero_predial_anterior");
				resultSetTemp = dbConnector.execQuery(
						"SELECT edificio FROM public.predio_ficha WHERE t_id=" + resultSet.getString("t_id"),
						dbConnector.conn);
				resultSetTemp.next();
				MUTA_EDIFICIO_ANT = resultSetTemp.getString("edificio");
				resultSetTemp = dbConnector.execQuery(
						"SELECT unidad FROM public.predio_ficha WHERE t_id=" + resultSet.getString("t_id"),
						dbConnector.conn);
				resultSetTemp.next();
				MUTA_UND_PRED_ANT = resultSetTemp.getString("unidad");
				resultSetTemp = dbConnector.execQuery(
						"SELECT categoria_suelo_pot FROM public.predio_ficha WHERE t_id=" + resultSet.getString("t_id"),
						dbConnector.conn);
				resultSetTemp.next();
				MUTA_CLASIF_SUELO_ANT = resultSetTemp.getString("categoria_suelo_pot");
				basicLine = MUTA_ID_REG + MUTA_VIGENCIA + MUTA_TIPO_RESO + MUTA_RESOLUC + MUTA_NRO_FICHA + MUTA_NRO_REG
						+ MUTA_MPIO + MUTA_SECTOR + MUTA_CORREG + MUTA_BARRIO + MUTA_MANZ_VERE + MUTA_PREDIO
						+ MUTA_EDIFICIO + MUTA_UND_PRED + MUTA_AREA_TERR + MUTA_DIRECCION + MUTA_CARACTERIST
						+ MUTA_CLASIF_SUELO + MUTA_COEFIC_PREDIO + MUTA_COEFIC_EDIFIC + MUTA_MPIO_ANT + MUTA_SECTOR_ANT
						+ MUTA_CORREG_ANT + MUTA_BARRIO_ANT + MUTA_MANZ_VERED_ANT + MUTA_PREDIO_ANT + MUTA_EDIFICIO_ANT
						+ MUTA_UND_PRED_ANT + MUTA_CLASIF_SUELO_ANT;
				tableData.add(basicLine);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tableData;

	}

	public ArrayList<String> getInformationTable3(DBConnector dbConnector, List<String> baseInformation) { // Destmuta
		ArrayList<String> tableData = new ArrayList<String>();
		ResultSet resultSet = null;
		ResultSet resultSetTemp = null;
		String DEMU_ID_REG = "3";
		String DEMU_VIGENCIA = baseInformation.get(0);
		String DEMU_TIPO_RESO = "184";
		String DEMU_RESOLUC = baseInformation.get(1);
		String DEMU_NRO_FICHA = "";
		String DEMU_NRO_REG = baseInformation.get(5);// (5)
		String DEMU_DESTI_ECO = "";
		String DEMU_PORC_DEST = "000";
		String basicLine = "";
		try {
			resultSet = dbConnector.execQuery("SELECT * FROM public.predio", dbConnector.conn);
			while (resultSet.next()) {
				DEMU_NRO_FICHA = resultSet.getString("nupre");// (9)
				resultSetTemp = dbConnector.execQuery("SELECT destino_econo FROM public.unidad_construccion WHERE t_id="
						+ resultSet.getString("t_id"), dbConnector.conn);
				resultSetTemp.next();
				DEMU_DESTI_ECO = resultSetTemp.getString("destino_econo");
				basicLine = DEMU_ID_REG + DEMU_VIGENCIA + DEMU_TIPO_RESO + DEMU_RESOLUC + DEMU_NRO_FICHA + DEMU_NRO_REG
						+ DEMU_DESTI_ECO + DEMU_PORC_DEST;
				tableData.add(basicLine);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tableData;
	}

	public ArrayList<String> getInformationTable4(DBConnector dbConnector, List<String> baseInformation) { // Mutaprop
		ArrayList<String> tableData = new ArrayList<String>();
		ResultSet resultSet = null;
		ResultSet resultSetTemp = null;
		String MUPO_ID_REG = "4";
		String MUPO_VIGENCIA = baseInformation.get(0);
		String MUPO_TIPO_RESO = "184";
		String MUPO_RESOLUC = baseInformation.get(1);
		String MUPO_NRO_FICHA = "";
		String MUPO_NRO_REG = baseInformation.get(5);
		String MUPO_TIPO_DOC = "";
		String MUPO_DOCUMENTO = "";
		String MUPO_PRIMER_APELLIDO = "";
		String MUPO_NOMBRE = "";
		String MUPO_DERECHO = "";
		String MUPO_NOTARIA = "xxxxx xxxx";
		String MUPO_ESCRITURA = "0000000";
		String MUPO_FECHA_ESCRIT = "00/00/0000"; // DD/MM/AAAA
		String MUPO_FECHA_REGIST = "00/00/0000"; // DD/MM/AAAA
		String MUPO_TOMO = "000";
		String MUPO_MATRICULA = "";
		String MUPO_CALIDAD_PROP = "00";
		String MUPO_GRAVABLE = "0";
		String MUPO_ADQUISICION = "0";
		String MUPO_LITIGIO = "0";
		String MUPO_PORC_LITIGIO = "00000";
		String MUPO_SEGUNDO_APELLIDO = "";
		String MUPO_RAZON_COMERCIAL = "XXXX XXXX XXXX XXXXX";
		String MUPO_GENERO = "";
		String basicLine = "";
		try {
			resultSet = dbConnector.execQuery("SELECT * FROM public.predio", dbConnector.conn);
			while (resultSet.next()) {
				MUPO_NRO_FICHA = resultSet.getString("nupre");// (9)
				resultSetTemp = dbConnector.execQuery("SELECT tipo_documento FROM public.interesado_natural WHERE t_id="
						+ resultSet.getString("t_id"), dbConnector.conn);
				resultSetTemp.next();
				MUPO_TIPO_DOC = resultSetTemp.getString("tipo_documento");
				resultSetTemp = dbConnector
						.execQuery("SELECT documento_identidad FROM public.interesado_natural WHERE t_id="
								+ resultSet.getString("t_id"), dbConnector.conn);
				resultSetTemp.next();
				MUPO_DOCUMENTO = resultSetTemp.getString("documento_identidad");
				resultSetTemp = dbConnector
						.execQuery("SELECT primer_apellido FROM public.interesado_natural WHERE t_id="
								+ resultSet.getString("t_id"), dbConnector.conn);
				resultSetTemp.next();
				MUPO_PRIMER_APELLIDO = resultSetTemp.getString("primer_apellido");
				resultSetTemp = dbConnector.execQuery(
						"SELECT primer_nombre FROM public.interesado_natural WHERE t_id=" + resultSet.getString("t_id"),
						dbConnector.conn);
				resultSetTemp.next();
				MUPO_NOMBRE = resultSetTemp.getString("primer_nombre");
				resultSetTemp = dbConnector
						.execQuery("SELECT codigo_registral_derecho FROM public.col_derecho WHERE t_id="
								+ resultSet.getString("t_id"), dbConnector.conn);
				resultSetTemp.next();
				MUPO_DERECHO = resultSetTemp.getString("codigo_registral_derecho");
				resultSetTemp = dbConnector.execQuery(
						"SELECT fmi FROM public.predio WHERE t_id=" + resultSet.getString("t_id"), dbConnector.conn);
				resultSetTemp.next();
				MUPO_MATRICULA = resultSetTemp.getString("fmi");
				resultSetTemp = dbConnector
						.execQuery("SELECT segundo_apellido FROM public.interesado_natural WHERE t_id="
								+ resultSet.getString("t_id"), dbConnector.conn);
				resultSetTemp.next();
				MUPO_SEGUNDO_APELLIDO = resultSetTemp.getString("segundo_apellido");
				resultSetTemp = dbConnector.execQuery(
						"SELECT genero FROM public.interesado_natural WHERE t_id=" + resultSet.getString("t_id"),
						dbConnector.conn);
				resultSetTemp.next();
				MUPO_GENERO = resultSetTemp.getString("genero");
				basicLine = MUPO_ID_REG + MUPO_VIGENCIA + MUPO_TIPO_RESO + MUPO_RESOLUC + MUPO_NRO_FICHA + MUPO_NRO_REG
						+ MUPO_TIPO_DOC + MUPO_DOCUMENTO + MUPO_PRIMER_APELLIDO + MUPO_NOMBRE + MUPO_DERECHO
						+ MUPO_NOTARIA + MUPO_ESCRITURA + MUPO_FECHA_ESCRIT + MUPO_FECHA_REGIST + MUPO_TOMO
						+ MUPO_MATRICULA + MUPO_CALIDAD_PROP + MUPO_GRAVABLE + MUPO_ADQUISICION + MUPO_LITIGIO
						+ MUPO_PORC_LITIGIO + MUPO_SEGUNDO_APELLIDO + MUPO_RAZON_COMERCIAL + MUPO_GENERO;
				tableData.add(basicLine);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tableData;
	}

	public ArrayList<String> getInformationTable5(DBConnector dbConnector, List<String> baseInformation) { // Mutacons
		ArrayList<String> tableData = new ArrayList<String>();
		ResultSet resultSet = null;
		ResultSet resultSetTemp = null;
		String MUCO_ID_REG = "5";
		String MUCO_VIGENCIA = baseInformation.get(0);
		String MUCO_TIPO_RESO = "184";
		String MUCO_RESOLUC = baseInformation.get(1);
		String MUCO_NRO_FICHA = "";
		String MUCO_NRO_REG = baseInformation.get(5);
		String MUCO_NRO_CONST = "00000";
		String MUCO_PUNTOS = "";
		String MUCO_AREA_CONST = "";
		String MUCO_MEJORA = "";
		String MUCO_LEY56 = "0";
		String MUCO_IDENTIFICADOR = "000";
		String MUCO_ACUEDUCTO = "0";
		String MUCO_TELEFONO = "0";
		String MUCO_ALCANTARILLADO = "0";
		String MUCO_ENERGIA_ELEC = "0";
		String MUCO_GAS = "0";
		String MUCO_NRO_PISOS = "";
		String MUCO_PORC_CONSTR = "000";
		String MUCO_EDAD_CONSTR = "";
		String basicLine = "";
		try {
			resultSet = dbConnector.execQuery("SELECT * FROM public.predio", dbConnector.conn);
			while (resultSet.next()) {
				MUCO_NRO_FICHA = resultSet.getString("nupre");// (9)
				resultSetTemp = dbConnector.execQuery(
						"SELECT puntuacion FROM public.unidad_construccion WHERE t_id=" + resultSet.getString("t_id"),
						dbConnector.conn);
				resultSetTemp.next();
				MUCO_PUNTOS = resultSetTemp.getString("puntuacion");
				resultSetTemp = dbConnector
						.execQuery("SELECT area_construida FROM public.unidadconstruccion WHERE t_id="
								+ resultSet.getString("t_id"), dbConnector.conn);
				resultSetTemp.next();
				MUCO_AREA_CONST = resultSetTemp.getString("area_construida");
				resultSetTemp = dbConnector
						.execQuery("SELECT tipo_construccion FROM public.unidadconstruccion WHERE t_id="
								+ resultSet.getString("t_id"), dbConnector.conn);
				resultSetTemp.next();
				MUCO_MEJORA = resultSetTemp.getString("tipo_construccion");
				resultSetTemp = dbConnector.execQuery(
						"SELECT numero_pisos FROM public.unidadconstruccion WHERE t_id=" + resultSet.getString("t_id"),
						dbConnector.conn);
				resultSetTemp.next();
				MUCO_NRO_PISOS = resultSetTemp.getString("numero_pisos");
				resultSetTemp = dbConnector
						.execQuery("SELECT anio_construction FROM public.unidad_construccion WHERE t_id="
								+ resultSet.getString("t_id"), dbConnector.conn);
				resultSetTemp.next();
				MUCO_EDAD_CONSTR = resultSetTemp.getString("anio_construction");
				basicLine = MUCO_ID_REG + MUCO_VIGENCIA + MUCO_TIPO_RESO + MUCO_RESOLUC + MUCO_NRO_FICHA + MUCO_NRO_REG
						+ MUCO_NRO_CONST + MUCO_PUNTOS + MUCO_AREA_CONST + MUCO_MEJORA + MUCO_LEY56 + MUCO_IDENTIFICADOR
						+ MUCO_ACUEDUCTO + MUCO_TELEFONO + MUCO_ALCANTARILLADO + MUCO_ENERGIA_ELEC + MUCO_GAS
						+ MUCO_NRO_PISOS + MUCO_PORC_CONSTR + MUCO_EDAD_CONSTR;
				;
				tableData.add(basicLine);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tableData;
	}

	public ArrayList<String> getInformationTable6(DBConnector dbConnector, List<String> baseInformation) { // Mutacali
		ArrayList<String> tableData = new ArrayList<String>();
		ResultSet resultSet = null;
		ResultSet resultSetTemp = null;
		String MUCA_ID_REG = "6";
		String MUCA_VIGENCIA = baseInformation.get(0);
		String MUCA_TIPO_RESO = "184";
		String MUCA_RESOLUC = baseInformation.get(1);
		String MUCA_NRO_FICHA = "";
		String MUCA_NRO_REG = baseInformation.get(5);
		String MUCA_NRO_CONST = "00000";
		String MUCA_TIPO_CONST = "";
		String MUCA_CODIGO_CALI = "0000";
		String MUCA_PUNTOS = "000";
		String basicLine = "";
		try {
			resultSet = dbConnector.execQuery("SELECT * FROM public.predio", dbConnector.conn);
			while (resultSet.next()) {
				MUCA_NRO_FICHA = resultSet.getString("nupre");// (9)
				resultSetTemp = dbConnector
						.execQuery("SELECT tipo_construccion FROM public.unidadconstruccion WHERE t_id="
								+ resultSet.getString("t_id"), dbConnector.conn);
				resultSetTemp.next();
				MUCA_TIPO_CONST = resultSetTemp.getString("tipo_construccion");
				basicLine = MUCA_ID_REG + MUCA_VIGENCIA + MUCA_TIPO_RESO + MUCA_RESOLUC + MUCA_NRO_FICHA + MUCA_NRO_REG
						+ MUCA_NRO_CONST + MUCA_TIPO_CONST + MUCA_CODIGO_CALI + MUCA_PUNTOS;
				tableData.add(basicLine);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tableData;
	}

	public ArrayList<String> getInformationTable7(DBConnector dbConnector, List<String> baseInformation) { // Lindmuta
		ArrayList<String> tableData = new ArrayList<String>();
		ResultSet resultSet = null;
		ResultSet resultSetTemp = null;
		String LIMU_ID_REG = "7";
		String LIMU_VIGENCIA = baseInformation.get(0);
		String LIMU_TIPO_RESO = "184";
		String LIMU_RESOLUC = baseInformation.get(1);
		String LIMU_NRO_FICHA = "";
		String LIMU_NRO_REG = baseInformation.get(5);
		String LIMU_PUNTO_CARDINAL = "XX";
		String LIMU_LINDERO = "XXXXXXXXX XXXXXXXXX XXXXXXXXXX";
		String basicLine = "";
		try {
			resultSet = dbConnector.execQuery("SELECT * FROM public.predio", dbConnector.conn);
			while (resultSet.next()) {
				LIMU_NRO_FICHA = resultSet.getString("nupre");// (9)
				basicLine = LIMU_ID_REG + LIMU_VIGENCIA + LIMU_TIPO_RESO + LIMU_RESOLUC + LIMU_NRO_FICHA + LIMU_NRO_REG
						+ LIMU_PUNTO_CARDINAL + LIMU_LINDERO;
				tableData.add(basicLine);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tableData;
	}

	public ArrayList<String> getInformationTable8(DBConnector dbConnector, List<String> baseInformation) { // Cartmuta
		ArrayList<String> tableData = new ArrayList<String>();
		ResultSet resultSet = null;
		ResultSet resultSetTemp = null;
		String CARM_ID_REG = "8";
		String CARM_VIGENCIA = baseInformation.get(0);
		String CARM_TIPO_RESO = "184";
		String CARM_RESOLUC = baseInformation.get(1);
		String CARM_NRO_FICHA = "";
		String CARM_NRO_REG = baseInformation.get(5);
		String CARM_PLANCHA = "               ";
		String CARM_VENTANA = "               ";
		String CARM_ESCALA_PLANCHA = "X:XXXX         ";
		String CARM_VIG_PLANCHA = "0000";
		String CARM_NROVUELO = "IGAC           ";
		String CARM_NROFAJA = "0              ";
		String CARM_NROFOTO = "000            ";
		String CARM_VIGFOTO = "0000";
		String CARM_AMPLIACION = "               ";
		String CARM_ESCALA_FOTO = "X:XXXX         ";
		String basicLine = "";
		try {
			resultSet = dbConnector.execQuery("SELECT * FROM public.predio", dbConnector.conn);
			while (resultSet.next()) {
				CARM_NRO_FICHA = resultSet.getString("nupre");// (9)
				basicLine = CARM_ID_REG + CARM_VIGENCIA + CARM_TIPO_RESO + CARM_RESOLUC + CARM_NRO_FICHA + CARM_NRO_REG
						+ CARM_PLANCHA + CARM_VENTANA + CARM_ESCALA_PLANCHA + CARM_VIG_PLANCHA + CARM_NROVUELO
						+ CARM_NROFAJA + CARM_NROFOTO + CARM_VIGFOTO + CARM_AMPLIACION + CARM_ESCALA_FOTO;
				tableData.add(basicLine);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tableData;
	}

	public String getTotalNumberOfReg(DBConnector dbConnector) {
		ResultSet resultSet = null;
		String total = "";
		resultSet = dbConnector.execQuery("SELECT COUNT(*) FROM public.predio", dbConnector.conn);
		try {
			resultSet.next();
			total = Integer.toString((resultSet.getInt(1) * 7) + 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.printf("Cantidad total de registros del archivo: " + total + "%n");
		return total;
	}

	public String getNumberOfReg(DBConnector dbConnector) {
		ResultSet resultSet = null;
		String total = "";
		resultSet = dbConnector.execQuery("SELECT COUNT(*) FROM public.predio", dbConnector.conn);
		try {
			resultSet.next();
			total = Integer.toString(resultSet.getInt(1));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.printf("Cantidad de registros por tabla: " + total + "%n");
		return total;
	}

	public String getAreaOfField(DBConnector dbConnector) {
		ResultSet resultSet = null;
		String total = "";
		int summatory = 0;
		/*
		 * resultSet =
		 * dbConnector.execQuery("SELECT area_calculada_plano_local FROM public.predio",
		 * dbConnector.conn);
		 * 
		 * try { while (resultSet.next()) { summatory = resultSet.getInt(5) + summatory;
		 * } total = Integer.toString(summatory);
		 * 
		 * } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		total = Integer.toString(summatory);
		System.out.printf("Cantidad area de terreno: " + total + "%n");
		return total = Integer.toString(summatory);
	}

	public String getAreaOfBuild(DBConnector dbConnector) {
		ResultSet resultSet = null;
		String total = "";
		int summatory = 0;
		resultSet = dbConnector.execQuery("SELECT area_construida FROM public.unidadconstruccion", dbConnector.conn);

		try {
			while (resultSet.next()) {
				summatory = resultSet.getInt("area_construida") + summatory;
			}
			total = Integer.toString(summatory);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.printf("Cantidad area construida: " + total + "%n");
		return total;
	}

	public String getSumOfPoints(DBConnector dbConnector) {
		ResultSet resultSet = null;
		String total = "";
		int summatory = 0;
		resultSet = dbConnector.execQuery("SELECT puntuacion FROM public.unidad_construccion", dbConnector.conn);

		try {
			while (resultSet.next()) {
				summatory = resultSet.getInt("puntuacion") + summatory;
			}
			total = Integer.toString(summatory);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.printf("Cantidad total puntos de construcción: " + total + "%n");
		return total;
	}
}

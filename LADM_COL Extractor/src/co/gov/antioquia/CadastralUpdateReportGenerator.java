package co.gov.antioquia;

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
	
	public void buildReport(DBConnector dbConnector, String RESO_VIGENCIA, String RESO_RESOLUC, String RESO_MPIO, String RESO_SECTOR, String RESO_NRO_REG, String RESO_AREA_TERRE, String RESO_AREA_CONS, String RESO_SUMA_PTOS) {
		List<String> baseInformation = new ArrayList<String>();
		baseInformation.add(RESO_VIGENCIA);
		baseInformation.add(RESO_RESOLUC);
		baseInformation.add(RESO_MPIO);
		baseInformation.add(RESO_SECTOR);
		baseInformation.add(this.getNumberOfReg(dbConnector));
		baseInformation.add(this.getAreaOfField(dbConnector));
		baseInformation.add(this.getAreaOfBuild(dbConnector));
		baseInformation.add(this.getSumOfPoints(dbConnector));
		getInformation(dbConnector, baseInformation);
	}
	
	public ArrayList<String> getInformationTable1(DBConnector dbConnector, List<String> baseInformation) { // Resoluci
		ArrayList<String> tableData = new ArrayList<String>();
		String RESO_ID_REG = "1";
		String RESO_VIGENCIA = baseInformation.get(1);
		String RESO_TIPO_RESO = "184";
		String RESO_RESOLUC = baseInformation.get(2);
		String RESO_MPIO = baseInformation.get(3);
		String RESO_SECTOR = baseInformation.get(4);
		String RESO_NRO_REG = baseInformation.get(5);
		String RESO_AREA_TERRE = baseInformation.get(6);
		String RESO_AREA_CONS = baseInformation.get(7);
		String RESO_SUMA_PTOS = baseInformation.get(8);
		String RESO_PARCIAL_TOTAL = "1";		
		String basicLine = RESO_ID_REG + RESO_VIGENCIA + RESO_TIPO_RESO + RESO_RESOLUC + RESO_MPIO + RESO_SECTOR + RESO_NRO_REG + RESO_AREA_TERRE + RESO_AREA_CONS + RESO_SUMA_PTOS + RESO_PARCIAL_TOTAL;
		tableData.add(basicLine);
		return tableData;
	}
	
	public ArrayList<String> getInformationTable2(DBConnector dbConnector, List<String> baseInformation) { // Mutacion
		ArrayList<String> tableData = new ArrayList<String>();
		//dbConnector.execQuery("SELECT * FROM public.col_derecho", dbConnector.conn);
		String MUTA_ID_REG = "2";//(1)
		String MUTA_VIGENCIA = baseInformation.get(1); //(4)
		String MUTA_TIPO_RESO = "184";//(3)
		String MUTA_RESOLUC = baseInformation.get(2);//(7)
		try {
			String MUTA_NRO_FICHA = dbConnector.execQuery("SELECT * FROM public.Predio", dbConnector.conn).getString("NUPRE");//(9)
			String MUTA_NRO_REG = "";//(5)
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// ;
		String MUTA_MPIO = baseInformation.get(3);//(3)
		String MUTA_SECTOR = baseInformation.get(4);//(1)
		try {
		String MUTA_CORREG = "000";
		String MUTA_BARRIO = dbConnector.execQuery("SELECT * FROM public.Predio", dbConnector.conn).getString("NUPRE");
		String MUTA_MANZ_VERE = "";
		String MUTA_PREDIO = "";
		String MUTA_EDIFICIO = "";
		String MUTA_UND_PRED = "";
		String MUTA_AREA_TERR = "";
		String MUTA_DIRECCION = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";//(49)Varchar2
		String MUTA_CARACTERIST = "";
		String MUTA_CLASIF_SUELO = "";
		String MUTA_COEFIC_PREDIO = "";
		String MUTA_COEFIC_EDIFIC = "";
		String MUTA_MPIO_ANT = "";
		String MUTA_SECTOR_ANT = "";
		String MUTA_CORREG_ANT = "";
		String MUTA_BARRIO_ANT = "";
		String MUTA_MANZ_VERED_ANT = "";
		String MUTA_PREDIO_ANT = "";
		String MUTA_EDIFICIO_ANT = "";
		String MUTA_UND_PRED_ANT = "";
		String MUTA_CLASIF_SUELO_ANT = "";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return tableData;
		
	}
	
	public ArrayList<String> getInformationTable3(DBConnector dbConnector, List<String> baseInformation) { // Destmuta
		ArrayList<String> tableData = new ArrayList<String>();
		String DEMU_ID_REG = "3";
		String DEMU_VIGENCIA = baseInformation.get(1);
		String DEMU_TIPO_RESO = "184";
		String DEMU_RESOLUC = baseInformation.get(2);
		String DEMU_NRO_FICHA = "";
		String DEMU_NRO_REG = "";
		String DEMU_DESTI_ECO = "";
		String DEMU_PORC_DEST = "";
		return tableData;
	}
	
	public ArrayList<String> getInformationTable4(DBConnector dbConnector, List<String> baseInformation) { // Mutaprop
		ArrayList<String> tableData = new ArrayList<String>();
		String MUPO_ID_REG = "4";
		String MUPO_VIGENCIA = baseInformation.get(1);
		String MUPO_TIPO_RESO = "184";
		String MUPO_RESOLUC = baseInformation.get(2);
		String MUPO_NRO_FICHA = "";
		String MUPO_NRO_REG = "";
		String MUPO_TIPO_DOC = "";
		String MUPO_DOCUMENTO = "";
		String MUPO_PRIMER_APELLIDO = "";
		String MUPO_NOMBRE = "";
		String MUPO_DERECHO = "";
		String MUPO_NOTARIA = "";
		String MUPO_ESCRITURA = "";
		String MUPO_FECHA_ESCRIT = ""; // DD/MM/AAAA
		String MUPO_FECHA_REGIST = ""; // DD/MM/AAAA
		String MUPO_TOMO = "";
		String MUPO_MATRICULA = "";
		String MUPO_CALIDAD_PROP = "";
		String MUPO_GRAVABLE = "";
		String MUPO_ADQUISICION = "";
		String MUPO_LITIGIO = "";
		String MUPO_PORC_LITIGIO = "";
		String MUPO_SEGUNDO_APELLIDO = "";
		String MUPO_RAZON_COMERCIAL = "";
		String MUPO_GENERO = "";
		return tableData;
	}
	
	public ArrayList<String> getInformationTable5(DBConnector dbConnector, List<String> baseInformation) { // Mutacons
		ArrayList<String> tableData = new ArrayList<String>();
		String MUCO_ID_REG = "5";
		String MUCO_VIGENCIA = baseInformation.get(1);
		String MUCO_TIPO_RESO = "184";
		String MUCO_RESOLUC = baseInformation.get(2);
		String MUCO_NRO_FICHA = "";
		String MUCO_NRO_REG = "";
		String MUCO_NRO_CONST = "";
		String MUCO_PUNTOS = "";
		String MUCO_AREA_CONST = "";
		String MUCO_MEJORA = "";
		String MUCO_LEY56 = "";
		String MUCO_IDENTIFICADOR = "";
		String MUCO_ACUEDUCTO = "";
		String MUCO_TELEFONO = "";
		String MUCO_ALCANTARILLADO = "";
		String MUCO_ENERGIA = "";
		String MUCO_GAS = "";
		String MUCO_NRO_PISOS = "";
		String MUCO_PORC_CONSTR = "";
		String MUCO_EDAD_CONSTR = "";
		return tableData;
	}
	
	public ArrayList<String> getInformationTable6(DBConnector dbConnector, List<String> baseInformation) { // Mutacali
		ArrayList<String> tableData = new ArrayList<String>();
		String MUCA_ID_REG = "6";
		String MUCA_VIGENCIA = baseInformation.get(1);
		String MUCA_TIPO_RESO = "184";
		String MUCA_RESOLUC = baseInformation.get(2);
		String MUCA_NRO_FICHA = "";
		String MUCA_NRO_REG = "";
		String MUCA_NRO_CONST = "";
		String MUCA_TIPO_CONST = "";
		String MUCA_CODIGO_CALI = "";
		String MUCA_PUNTOS = "";
		return tableData;
	}
	
	public ArrayList<String> getInformationTable7(DBConnector dbConnector, List<String> baseInformation) { // Lindmuta
		ArrayList<String> tableData = new ArrayList<String>();
		String LIMU_ID_REG = "7";
		String LIMU_VIGENCIA = baseInformation.get(1);
		String LIMU_TIPO_RESO = "184";
		String LIMU_RESOLUC = baseInformation.get(2);
		String LIMU_NRO_FICHA = "";
		String LIMU_NRO_REG = "";
		String LIMU_PUNTO_CARDINAL = "";
		String LIMU_LINDERO = "";
		return tableData;
	}
	
	public ArrayList<String> getInformationTable8(DBConnector dbConnector, List<String> baseInformation) { // Cartmuta
		ArrayList<String> tableData = new ArrayList<String>();
		String CARM_ID_REG = "8";
		String CARM_VIGENCIA = baseInformation.get(1);
		String CARM_TIPO_RESO = "184";
		String CARM_RESOLUC = baseInformation.get(2);
		String CARM_NRO_FICHA = "";
		String CARM_NRO_REG = "";
		String CARM_PLANCHA = "";
		String CARM_VENTANA = "";
		String CARM_ESCALA_PLANCHA = "";
		String CARM_VIG_PLANCHA = "";
		String CARM_NROVUELO = "";
		String CARM_NROFAJA = "";
		String CARM_NROFOTO = "";
		String CARM_VIGFOTO = "";
		String CARM_AMPLIACION = "";
		String CARM_ESCALA_FOTO = "";
		return tableData;
	}
	
	public String getNumberOfReg(DBConnector dbConnector) {
		String total = "";
		return total;
	}
	
	public String getAreaOfField(DBConnector dbConnector) {
		String total = "";
		return total;
	}
	
	public String getAreaOfBuild(DBConnector dbConnector) {
		String total = "";
		return total;
	}
	
	public String getSumOfPoints(DBConnector dbConnector) {
		String total = "";
		return total;
	}
}

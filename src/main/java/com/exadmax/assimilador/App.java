package com.exadmax.assimilador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

/**
 * Gerador de relatorio
 *
 */
public class App {

	public static final String pasta = "D:\\Planilhas\\";
	public static final String xls = pasta + "Levantamento_Imobiliario";
	public static final String people = pasta + "people.csv";
	public static final String cotador = pasta + "cotador.csv";
	public static final String operacao = pasta + "operacao.csv";

	public static void main(String[] args) throws IOException {
		
		
		
		List<DadosOperacao> dadosOperacao = extraiDadosOperacao();
		List<DadosCotador> dadosCotador = extraiDadosCotador();
		List<DadosPeople> dadosPeople = extrairDadosPeople();

		List<DadosTabela> listaFinal = new ArrayList<DadosTabela>();
		for (DadosPeople dado : dadosPeople) {
			DadosTabela dadoFinal = new DadosTabela();
			dadoFinal.setCdCorretor(dado.getCodigoCorretor());
			dadoFinal.setCdParceiro(dado.getCodigoParceiro());
			dadoFinal.setSituacaoTabela(dado.getStatusImobiliaria());
			dadoFinal.setDataInclusao(dado.getDataInclusao());
			dadoFinal.setNomeCorretor(dado.getNomeCorretor());
			dadoFinal.setNomeParceiro(dado.getNomeParceiro());
			for (DadosOperacao ope : dadosOperacao) {
				if (ope.getCdCorretor().equals(dadoFinal.getCdCorretor()) && ope.getCdParceiro().equals(dadoFinal.getCdParceiro())) {
					dadoFinal.setNomeSucursal(ope.getNomeSucursal());
					dadoFinal.setSituacaoOperacao(ope.getSituacaoOperacao());
					break;
				}
			}
			dadoFinal.setTipoTransmissao(new HashMap<Long, Map<String, Long>>());
			dadoFinal.getTipoTransmissao().put(44L, new HashMap<String, Long>());
			dadoFinal.getTipoTransmissao().put(43L, new HashMap<String, Long>());
			for (DadosCotador cot : dadosCotador) {
				if (cot.getCdCorretor().equals(dadoFinal.getCdCorretor()) && cot.getCdParceiro().equals(dadoFinal.getCdParceiro())) {
					dadoFinal.getTipoTransmissao().get(cot.getProduto()).put(cot.getTipoTransacao(), cot.getItensTransmitidos());
				}
			}
			listaFinal.add(dadoFinal);
		}
		FileOutputStream fos = null;
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet firstSheet = workbook.createSheet("Relatorio");

			String nomeArquivo = xls + "_" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "" + (Calendar.getInstance().get(Calendar.YEAR)) + ".xls";
			fos = new FileOutputStream(new File(nomeArquivo));

			/*
			 * Este trecho obtem uma lista de objetos do tipo CD do banco de
			 * dados através de um DAO e itera sobre a lista criando linhas e
			 * colunas em um arquivo Excel com o conteúdo dos objetos.
			 */

			// criando cabecalho
			HSSFRow row0 = firstSheet.createRow(0);
			row0.createCell(0).setCellValue("Dados Gerais");
			row0.createCell(9).setCellValue("Indicadores Residencial");
			row0.createCell(14).setCellValue("Indicadores Empresarial");
			firstSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
			firstSheet.addMergedRegion(new CellRangeAddress(0, 0, 9, 13));
			firstSheet.addMergedRegion(new CellRangeAddress(0, 0, 14, 18));

			int l = 0;
			int h = 1;
			int d = 2;
			HSSFRow row1 = firstSheet.createRow(h);
			row1.createCell(l++).setCellValue("Cód Parceiro");// 0
			row1.createCell(l++).setCellValue("Nome Parceiro");// 1
			row1.createCell(l++).setCellValue("Cód Corretor");// 2
			row1.createCell(l++).setCellValue("Nome Corretor");// 3
			row1.createCell(l++).setCellValue("Cód Sucursal");// 4
			row1.createCell(l++).setCellValue("Nome Sucursal");// 5
			row1.createCell(l++).setCellValue("Situação Imobiliária");// 6
			row1.createCell(l++).setCellValue("Situação Operação");// 7
			row1.createCell(l++).setCellValue("Data de Inclusão");// 8

			row1.createCell(l++).setCellValue("Transmitidos Res");// 9
			row1.createCell(l++).setCellValue("Calculados Res");// 10
			row1.createCell(l++).setCellValue("Efetivados Res");// 11
			row1.createCell(l++).setCellValue("Recusados Res");// 12
			row1.createCell(l++).setCellValue("Pendentes Res");// 13

			row1.createCell(l++).setCellValue("Transmitidos Emp");// 14
			row1.createCell(l++).setCellValue("Calculados Emp");// 15
			row1.createCell(l++).setCellValue("Efetivados Emp");// 16
			row1.createCell(l++).setCellValue("Recusados Emp");// 17
			row1.createCell(l++).setCellValue("Pendentes Emp");// 18
			for (DadosTabela cd : listaFinal) {
				HSSFRow row = firstSheet.createRow(d);
				int j = 0;
				row.createCell(j++).setCellValue(cd.getCdParceiro());
				row.createCell(j++).setCellValue(cd.getNomeParceiro());
				row.createCell(j++).setCellValue(cd.getCdCorretor());
				row.createCell(j++).setCellValue(cd.getNomeCorretor());
				row.createCell(j++).setCellValue("");
				row.createCell(j++).setCellValue(cd.getNomeSucursal());
				row.createCell(j++).setCellValue(cd.getSituacaoTabela());
				if (cd.getSituacaoOperacao() != null) {
					if (cd.getSituacaoOperacao().equals("PEN")) {
						row.createCell(j++).setCellValue("Pendente");
					} else if (cd.getSituacaoOperacao().equals("BLO")) {
						row.createCell(j++).setCellValue("Bloqueado");
					} else if (cd.getSituacaoOperacao().equals("ATV")) {
						row.createCell(j++).setCellValue("Ativo");
					} else if (cd.getSituacaoOperacao().equals("PLI")) {
						row.createCell(j++).setCellValue("Pendente de Li e Concordo");
					} else if (cd.getSituacaoOperacao().equals("CAN")) {
						row.createCell(j++).setCellValue("Cancelado");
					}

				} else {
					row.createCell(j++).setCellValue("Não cadastrado");
				}
				Long codigoProduto = 44l;
				row.createCell(j++).setCellValue(cd.getDataInclusao());
				if (cd.getTipoTransmissao().containsKey(codigoProduto) && cd.getTipoTransmissao().get(codigoProduto).containsKey("TRA")) {
					row.createCell(j++).setCellValue(cd.getTipoTransmissao().get(codigoProduto).get("TRA"));
				} else {
					row.createCell(j++).setCellValue("");
				}

				if (cd.getTipoTransmissao().containsKey(codigoProduto) && cd.getTipoTransmissao().get(codigoProduto).containsKey("CAL")) {
					row.createCell(j++).setCellValue(cd.getTipoTransmissao().get(codigoProduto).get("CAL"));
				} else {
					row.createCell(j++).setCellValue("");
				}

				if (cd.getTipoTransmissao().containsKey(codigoProduto) && cd.getTipoTransmissao().get(codigoProduto).containsKey("EFE")) {
					row.createCell(j++).setCellValue(cd.getTipoTransmissao().get(codigoProduto).get("EFE"));
				} else {
					row.createCell(j++).setCellValue("");
				}

				if (cd.getTipoTransmissao().containsKey(codigoProduto) && cd.getTipoTransmissao().get(codigoProduto).containsKey("REC")) {
					row.createCell(j++).setCellValue(cd.getTipoTransmissao().get(codigoProduto).get("REC"));
				} else {
					row.createCell(j++).setCellValue("");
				}

				if (cd.getTipoTransmissao().containsKey(codigoProduto) && cd.getTipoTransmissao().get(codigoProduto).containsKey("PEN")) {
					row.createCell(j++).setCellValue(cd.getTipoTransmissao().get(codigoProduto).get("PEN"));
				} else {
					row.createCell(j++).setCellValue("");
				}
				codigoProduto = 43l;
				if (cd.getTipoTransmissao().containsKey(codigoProduto) && cd.getTipoTransmissao().get(codigoProduto).containsKey("TRA")) {
					row.createCell(j++).setCellValue(cd.getTipoTransmissao().get(codigoProduto).get("TRA"));
				} else {
					row.createCell(j++).setCellValue("");
				}
				if (cd.getTipoTransmissao().containsKey(codigoProduto) && cd.getTipoTransmissao().get(codigoProduto).containsKey("CAL")) {
					row.createCell(j++).setCellValue(cd.getTipoTransmissao().get(codigoProduto).get("CAL"));
				} else {
					row.createCell(j++).setCellValue("");
				}
				if (cd.getTipoTransmissao().containsKey(codigoProduto) && cd.getTipoTransmissao().get(codigoProduto).containsKey("EFE")) {
					row.createCell(j++).setCellValue(cd.getTipoTransmissao().get(codigoProduto).get("EFE"));
				} else {
					row.createCell(j++).setCellValue("");
				}
				if (cd.getTipoTransmissao().containsKey(codigoProduto) && cd.getTipoTransmissao().get(codigoProduto).containsKey("REC")) {
					row.createCell(j++).setCellValue(cd.getTipoTransmissao().get(codigoProduto).get("REC"));
				} else {
					row.createCell(j++).setCellValue("");
				}
				if (cd.getTipoTransmissao().containsKey(codigoProduto) && cd.getTipoTransmissao().get(codigoProduto).containsKey("PEN")) {
					row.createCell(j++).setCellValue(cd.getTipoTransmissao().get(codigoProduto).get("PEN"));
				} else {
					row.createCell(j++).setCellValue("");
				}
				d++;

			} // fim do for

			workbook.write(fos);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao exportar arquivo");
		} finally {
			try {
				fos.flush();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private static List<DadosPeople> extrairDadosPeople() throws IOException {
		CSVReader readerPeople = new CSVReader(new FileReader(people));
		List<DadosPeople> listPeople = new ArrayList<DadosPeople>();
		String[] nextLine;
		while ((nextLine = readerPeople.readNext()) != null) {
			DadosPeople people = new DadosPeople();
			people.setDataInclusao(nextLine[0]);
			people.setCodigoParceiro(Long.valueOf(nextLine[1]));
			people.setNomeParceiro(nextLine[2]);
			people.setCodigoCorretor(Long.valueOf(nextLine[3]));
			people.setNomeCorretor(nextLine[4]);
			people.setStatusImobiliaria(nextLine[5]);
			listPeople.add(people);
		}
		readerPeople.close();
		return listPeople;
	}

	private static List<DadosCotador> extraiDadosCotador() throws FileNotFoundException {
		CSVReader readerCotador = new CSVReader(new FileReader(cotador));

		ColumnPositionMappingStrategy stratCotador = new ColumnPositionMappingStrategy();
		stratCotador.setType(DadosCotador.class);
		String[] columnsCotador = new String[] { "itensTransmitidos", "produto", "cdCorretor", "cdParceiro", "tipoTransacao" };
		stratCotador.setColumnMapping(columnsCotador);

		CsvToBean csvCotador = new CsvToBean();
		List<DadosCotador> listCotador = csvCotador.parse(stratCotador, readerCotador);
		return listCotador;
	}

	private static List<DadosOperacao> extraiDadosOperacao() throws FileNotFoundException {
		CSVReader reader = new CSVReader(new FileReader(operacao));
		ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
		strat.setType(DadosOperacao.class);

		String[] columns = new String[] { "cdParceiro", "nomeParceiro", "cdCorretor", "situacaoOperacao", "nomeCorretor", "nomeSucursal" };
		strat.setColumnMapping(columns);

		CsvToBean csv = new CsvToBean();
		List<DadosOperacao> list = csv.parse(strat, reader);
		return list;
	}
}

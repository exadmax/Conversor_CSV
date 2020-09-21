package com.exadmax.assimilador;

import java.util.Map;

public class DadosTabela {

	private Long cdParceiro;
	private String nomeParceiro;
	private Long cdCorretor;
	private String nomeCorretor;
	private Long cdSucursal;
	private String nomeSucursal;
	private String situacaoTabela;
	private String situacaoOperacao;
	private String dataInclusao;
	private Map<Long,Map<String,Long>>tipoTransmissao; 
	public Long getCdParceiro() {
		return cdParceiro;
	}
	public void setCdParceiro(Long cdParceiro) {
		this.cdParceiro = cdParceiro;
	}
	public String getNomeParceiro() {
		return nomeParceiro;
	}
	public void setNomeParceiro(String nomeParceiro) {
		this.nomeParceiro = nomeParceiro;
	}
	public Long getCdCorretor() {
		return cdCorretor;
	}
	public void setCdCorretor(Long cdCorretor) {
		this.cdCorretor = cdCorretor;
	}
	public String getNomeCorretor() {
		return nomeCorretor;
	}
	public void setNomeCorretor(String nomeCorretor) {
		this.nomeCorretor = nomeCorretor;
	}
	public Long getCdSucursal() {
		return cdSucursal;
	}
	public void setCdSucursal(Long cdSucursal) {
		this.cdSucursal = cdSucursal;
	}
	public String getNomeSucursal() {
		return nomeSucursal;
	}
	public void setNomeSucursal(String nomeSucursal) {
		this.nomeSucursal = nomeSucursal;
	}
	public String getSituacaoTabela() {
		return situacaoTabela;
	}
	public void setSituacaoTabela(String situacaoTabela) {
		this.situacaoTabela = situacaoTabela;
	}
	public String getSituacaoOperacao() {
		return situacaoOperacao;
	}
	public void setSituacaoOperacao(String situacaoOperacao) {
		this.situacaoOperacao = situacaoOperacao;
	}
	public String getDataInclusao() {
		return dataInclusao;
	}
	public void setDataInclusao(String dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
	public Map<Long, Map<String, Long>> getTipoTransmissao() {
		return tipoTransmissao;
	}
	public void setTipoTransmissao(Map<Long, Map<String, Long>> tipoTransmissao) {
		this.tipoTransmissao = tipoTransmissao;
	}
	@Override
	public String toString() {
		return "DadosTabela [cdParceiro=" + cdParceiro + ", nomeParceiro=" + nomeParceiro + ", cdCorretor=" + cdCorretor + ", nomeCorretor=" + nomeCorretor + ", cdSucursal=" + cdSucursal + ", nomeSucursal=" + nomeSucursal + ", situacaoTabela=" + situacaoTabela + ", situacaoOperacao=" + situacaoOperacao + ", dataInclusao=" + dataInclusao + ", tipoTransmissao=" + tipoTransmissao + "]";
	}
	
	
	
	

}

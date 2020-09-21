package com.exadmax.assimilador;

public class DadosOperacao {

	private Long cdParceiro;
	private String nomeParceiro;
	private Long cdCorretor;
	private String situacaoOperacao;
	private String nomeCorretor;	
	private String nomeSucursal;
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
	public String getSituacaoOperacao() {
		return situacaoOperacao;
	}
	public void setSituacaoOperacao(String situacaoOperacao) {
		this.situacaoOperacao = situacaoOperacao;
	}
	public String getNomeCorretor() {
		return nomeCorretor;
	}
	public void setNomeCorretor(String nomeCorretor) {
		this.nomeCorretor = nomeCorretor;
	}
	public String getNomeSucursal() {
		return nomeSucursal;
	}
	public void setNomeSucursal(String nomeSucursal) {
		this.nomeSucursal = nomeSucursal;
	}
	@Override
	public String toString() {
		return "DadosOperacao [cdParceiro=" + cdParceiro + ", nomeParceiro=" + nomeParceiro + ", cdCorretor=" + cdCorretor + ", situacaoOperacao=" + situacaoOperacao + ", nomeCorretor=" + nomeCorretor + ", nomeSucursal=" + nomeSucursal + "]";
	}
	

}

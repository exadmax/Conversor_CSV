package com.exadmax.assimilador;

public class DadosCotador {
	//ITENS_TRANSMITIDOS;CD_PRDUT_BASE;CD_CORRETOR;CD_PARCEIRO
	private Long itensTransmitidos;
	private Long produto;
	private Long cdCorretor;
	private Long cdParceiro;
	private String tipoTransacao;
	
	@Override
	public String toString() {
		return "DadosCotador [itensTransmitidos=" + itensTransmitidos + ", produto=" + produto + ", cdCorretor=" + cdCorretor + ", cdParceiro=" + cdParceiro + ", tipoTransacao=" + tipoTransacao + "]";
	}
	public Long getItensTransmitidos() {
		return itensTransmitidos;
	}
	public void setItensTransmitidos(Long itensTransmitidos) {
		this.itensTransmitidos = itensTransmitidos;
	}
	public Long getProduto() {
		return produto;
	}
	public void setProduto(Long produto) {
		this.produto = produto;
	}
	public Long getCdCorretor() {
		return cdCorretor;
	}
	public void setCdCorretor(Long cdCorretor) {
		this.cdCorretor = cdCorretor;
	}
	public Long getCdParceiro() {
		return cdParceiro;
	}
	public void setCdParceiro(Long cdParceiro) {
		this.cdParceiro = cdParceiro;
	}
	public String getTipoTransacao() {
		return tipoTransacao;
	}
	public void setTipoTransacao(String tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

}

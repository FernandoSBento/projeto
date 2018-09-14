package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import banco.DAOGenerico;
import modelo.Estado;

@ManagedBean
@ViewScoped
public class EstadoMB {

	private Estado estado = new Estado();
	private List<Estado> estados = new ArrayList<>();
	private DAOGenerico<Estado> dao = new DAOGenerico<>(Estado.class);

	public EstadoMB() {
		estados = dao.buscarTodos();
	}

	public void inserir() {
		if (estado.getId() == null) {
			dao.salvar(estado);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com Sucesso", ""));
		} else {
			dao.alterar(estado);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com Sucesso", ""));
		}
		estado = new Estado();
		estados = dao.buscarTodos();
	}

	public void excluir(Long id) {
		dao.excluir(id);
		estados = dao.buscarTodos();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com Sucesso", ""));
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

}

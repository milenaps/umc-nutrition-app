package com.nutri.java.view.bean;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectManyCheckbox;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.richfaces.component.html.HtmlCalendar;

import com.nutri.java.model.Usuario;
import com.nutri.java.services.IUsuarioService;

/**
 * ManageBean que encapsula as propriedades da entidade Usuario na camada view
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class UsuarioBean {

	/**
	 * Variavel de log
	 */
	private static Logger logger = Logger.getLogger(UsuarioBean.class);
	
	private Usuario usuario;
	private static IUsuarioService usuarioService;
	
	private String login;
	private String senha;
	
	private static HttpSession sessao;
	static Usuario usuarioLogado;
	
	private Boolean perfilPaciente;
	private Boolean perfilNutricionista;
	
	private int tipoAlimentacao;
	private int tracadoPerfil;

	private HtmlSelectManyCheckbox tipoAcessoBinding;
	private HtmlSelectOneRadio sexoBinding;
	private HtmlCalendar nasctoBinding;
	private HtmlInputText pesoBinding;
	private HtmlInputText alturaBinding;
	private HtmlSelectOneRadio tipoAlimBinding;
	private HtmlSelectOneRadio tipoPerfilBinding;
	private HtmlDataTable atividadesBinding;

	private HtmlInputText crnBinding;

	public Usuario getUsuario() {
		if (this.usuario.getId() == null) {
			if (usuarioLogado != null && usuarioLogado.getId() != null) {
				this.usuario = usuarioLogado;
			}
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public IUsuarioService getUsuarioService() {
		return usuarioService;
	}

	@SuppressWarnings("static-access")
	public void setUsuarioService(IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public void setTipoAlimentacao(int tipoAlimentacao) {
		this.tipoAlimentacao = tipoAlimentacao;
	}

	public int getTipoAlimentacao() {
		return tipoAlimentacao;
	}

	public void setTracadoPerfil(int tracadoPerfil) {
		this.tracadoPerfil = tracadoPerfil;
	}

	public int getTracadoPerfil() {
		return tracadoPerfil;
	}

	public HttpSession getSessao() {
		return sessao;
	}

	public void setPerfilPaciente(Boolean perfilPaciente) {
		this.perfilPaciente = perfilPaciente;
	}

	public Boolean getPerfilPaciente() {
		return perfilPaciente;
	}

	public void setPerfilNutricionista(Boolean perfilNutricionista) {
		this.perfilNutricionista = perfilNutricionista;
	}

	public Boolean getPerfilNutricionista() {
		return perfilNutricionista;
	}

	public void setTipoAcessoBinding(HtmlSelectManyCheckbox tipoAcessoBinding) {
		this.tipoAcessoBinding = tipoAcessoBinding;
	}

	public HtmlSelectManyCheckbox getTipoAcessoBinding() {
		return tipoAcessoBinding;
	}

	public HtmlSelectOneRadio getSexoBinding() {
		return sexoBinding;
	}

	public void setSexoBinding(HtmlSelectOneRadio sexoBinding) {
		this.sexoBinding = sexoBinding;
	}

	public HtmlCalendar getNasctoBinding() {
		return nasctoBinding;
	}

	public void setNasctoBinding(HtmlCalendar nasctoBinding) {
		this.nasctoBinding = nasctoBinding;
	}

	public HtmlInputText getPesoBinding() {
		return pesoBinding;
	}

	public void setPesoBinding(HtmlInputText pesoBinding) {
		this.pesoBinding = pesoBinding;
	}

	public HtmlInputText getAlturaBinding() {
		return alturaBinding;
	}

	public void setAlturaBinding(HtmlInputText alturaBinding) {
		this.alturaBinding = alturaBinding;
	}

	public HtmlSelectOneRadio getTipoAlimBinding() {
		return tipoAlimBinding;
	}

	public void setTipoAlimBinding(HtmlSelectOneRadio tipoAlimBinding) {
		this.tipoAlimBinding = tipoAlimBinding;
	}

	public HtmlSelectOneRadio getTipoPerfilBinding() {
		return tipoPerfilBinding;
	}

	public void setTipoPerfilBinding(HtmlSelectOneRadio tipoPerfilBinding) {
		this.tipoPerfilBinding = tipoPerfilBinding;
	}

	public HtmlDataTable getAtividadesBinding() {
		return atividadesBinding;
	}

	public void setAtividadesBinding(HtmlDataTable atividadesBinding) {
		this.atividadesBinding = atividadesBinding;
	}

	public HtmlInputText getCrnBinding() {
		return crnBinding;
	}

	public void setCrnBinding(HtmlInputText crnBinding) {
		this.crnBinding = crnBinding;
	}

	/**
	 * Recupera o usuario logado
	 * 
	 * @return O usuario
	 * @throws Exception
	 *             Lancada se houver problemas de conexao
	 */
	public Usuario getUsuarioLogado() {
		return getLoggedUser();
	}
	
	/**
	 * Recupera o usuario logado
	 * 
	 * @return O usuario
	 * @throws Exception
	 *             Lancada se houver problemas de conexao
	 */
	private static Usuario getLoggedUser() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletResponse rs = (HttpServletResponse) ctx.getExternalContext()
				.getResponse();
		if (sessao != null && usuarioLogado != null) {
			return usuarioLogado;
		} else {
			
			// XXX Verificar porque essa implementacao nao faz a mensagem aparecer na pagina
			FacesMessage msg = new FacesMessage();
			msg.setSummary("É necessário efetuar logon para utilizar a aplicação");
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
			ctx = FacesContext.getCurrentInstance();
			ctx.addMessage("NotAuthenticated", msg);
			// Fim do bloco a ser checado

			try {
				rs.sendRedirect("/NutriBem/pages/index.jsf");
			} catch (IOException e) {
				logger.warn("Erro durante o redirecionamento da pagina: " + e.getMessage(), e.getCause());
			}
			return null;
		}
	}

	/**
	 * Obtem as permissoes de acesso do usuario logado para exibicao das opcoes
	 * de menu
	 * 
	 * @return Uma lista de booleanos
	 */
	public boolean[] getRoles() {
		return getUserRoles();
	}
	
	private static boolean[] getUserRoles() {
		boolean[] lista = new boolean[3];
		if (usuarioLogado != null) {
			int totRoles = usuarioLogado.getLogin().getPerfis().size();
			for (int i = 0; i < totRoles; i++) {
				if (usuarioLogado.getLogin().getPerfis().get(i).getRole().equals("adm")) {
					lista[0] = true;
				}
				if (usuarioLogado.getLogin().getPerfis().get(i).getRole().equals("nut")) {
					lista[1] = true;
				}
				if (usuarioLogado.getLogin().getPerfis().get(i).getRole().equals("pac")) {
					lista[2] = true;
				}
			}
		}
		return lista;
	}

	/**
	 * Altera os campos obrigatorios na tela de cadastro de usuarios
	 */
	public void updateReqFields(ActionEvent e) {
		logger.info("UsuarioBean.updateReqFields() >> Passou por esse metodo");
	}

	/**
	 * Realiza a autenticacao na aplicacao se os dados do usuario forem corretos
	 * 
	 * @return A pagina redirecionada
	 */
	public String efetuarLogon() {
		try {
			FacesContext ctx = FacesContext.getCurrentInstance();
			
			if (usuarioService.validarLogin(login, senha)) {
				usuario = usuarioService.findUserByLogin(login);
				if (usuarioService.validarUsuario(usuario)) {
					HttpServletRequest req = (HttpServletRequest) ctx
							.getExternalContext().getRequest();
					sessao = req.getSession();
					sessao.setAttribute("login", usuario);
					usuarioLogado = (Usuario) sessao.getAttribute("login");
					
					// Limpando os campos associados ao usuário
					this.login = null;
					this.senha = null;
					
					return "aplicacao";
				} else {
					FacesMessage msg = new FacesMessage();
					msg.setSummary("Prezado(a) nutricionista, o seu cadastro ainda não foi examinado para lhe permitir acesso. Por favor tente novamente mais tarde.");
					msg.setSeverity(FacesMessage.SEVERITY_INFO);
					ctx.addMessage("NutriNaoAvaliado", msg);
					
					// Limpando os campos associados ao usuario
					this.login = null;
					this.senha = null;
					
					return "portal";
				}
			} else {
				FacesMessage msg = new FacesMessage();
				msg.setSummary("Usuário e/ou senha inválidos");
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				ctx.addMessage("Logon", msg);
				
				// Limpando os campos associados ao usuario
				this.login = null;
				this.senha = null;
				
				return "portal";
			}
		} catch (Exception e) {
			logger.error("Erro durante a validacao do usuario: " + e.getMessage(), e.getCause());
			return "portal";
		}
	}

	/**
	 * Realiza logoff na aplicacao e limpa a sessao
	 * 
	 * @return A pagina redirecionada
	 */
	public String efetuarLogoff() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage();
		msg.setSummary("Usuário desconectado com sucesso");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		ctx.addMessage("Logoff", msg);
		if (sessao != null) {
			sessao = null;
		}
		// Limpando os campos associados ao usuario
		this.usuario = new Usuario();
		usuarioLogado = this.usuario;
		
		return "portal";
	}

	public String save() {
		if (usuario.getId() == null) {
			usuarioService.save(usuario);
		} else {
			usuarioService.update(usuario);
		}
		this.usuario = new Usuario();
		return "sucesso";
	}

	public String delete() {
		usuarioService.delete(usuario);
		return null;
	}

	public List<Usuario> getAll() {
		return usuarioService.listAll();
	}
}

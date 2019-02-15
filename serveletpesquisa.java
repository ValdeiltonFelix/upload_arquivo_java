package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import beans.Produto;
import beans.ValidaLogin;
import dao.DaoUsuario;

/**
 * Servlet implementation class serveletpesquisa
 */
@WebServlet(urlPatterns={"/serveletpesquisa","/carregardados"})
public class serveletpesquisa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public serveletpesquisa() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String acao=request.getParameter("acao");
		 String id=request.getParameter("idarquivo");
		 String type=request.getParameter("type");
		 String nomearq=request.getParameter("nomearq");
		
		 
		 String dadosString=request.getParameter("dados");
		 
		if(acao!=null && acao.equals("salvasessaodoarquivo")){
			HttpSession sess=request.getSession();
			
			            sess.setAttribute("idcadastroarquivo", id);
			            sess.setAttribute("type", type);
			            sess.setAttribute("nomearq", nomearq);
			
		}
		 String output="";
		 DaoUsuario DaoUs=new DaoUsuario();
		 ValidaLogin carregar=new ValidaLogin(); 

		 List<ValidaLogin> listaLogins=new ArrayList<ValidaLogin>();
		 listaLogins=DaoUs.listaUsuarioNome(dadosString);
		 output="<ul class='list-unstyled'>";
		
		 
		 for (ValidaLogin validaLogin : listaLogins) {	 
			 	output+="<li>"+validaLogin.getId()+"."+validaLogin.getNome()+"</li>";
				
		    }
		
		
		 if(listaLogins.isEmpty()){
			output+="<li> Sem resultado !!</li>";
		}
		
		output+="</ul>"; 
		response.getWriter().print(output);
	}

}

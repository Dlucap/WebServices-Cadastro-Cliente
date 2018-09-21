package WS;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import ModeloBeans.ClienteBeans;
import ModeloDao.ClienteDao;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author Daniel Lucas
 */
@Path("CadastroCliente")
public class ClienteWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ClienteWS
     */
    public ClienteWS() {
    }
//

    /**
     * Retrieves representation of an instance of ws.SuperMercado
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of SuperMercado
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    /*Funcional*/
    @GET
    @Produces("application/json")
    @Path("Cliente/{cliente}")
    public String getBuscaCliente(@PathParam("cliente") String nomeCliente) {
        ClienteBeans clienteBeans = new ClienteBeans();

        clienteBeans.setNomeCliente(nomeCliente);
        ClienteDao clienteDao = new ClienteDao();
        System.out.println("NOME CLINETE " + nomeCliente + " clienteBenas: " + clienteBeans.getNomeCliente());
        clienteBeans = clienteDao.buscar(clienteBeans);

        //converter para Json
        Gson g = new Gson();
        return g.toJson(clienteBeans);
    }

    /*Funcional*/
    @GET
    @Produces("application/json")
    @Path("Cliente/ListaCliente")
    public String ListarCliente() {
        List<ClienteBeans> lista;

        ClienteDao clienteDao = new ClienteDao();
        lista = clienteDao.listar();

        //converter para Json
        Gson g = new Gson();
        return g.toJson(lista);
    }

    /*funcional*/
    @POST
    @Consumes({"application/json"})
    @Path("Cliente/InserirCliente")
    public boolean Inserir(String content) {
        Gson g = new Gson();
        System.out.println("Json:\n\n" + content);
        ClienteBeans clienteBeans = (ClienteBeans) g.fromJson(content, ClienteBeans.class);
        ClienteDao clienteDao = new ClienteDao();
        return clienteDao.inserir(clienteBeans);
    }

    /**
     * PUT method for updating or creating an instance of FazendaWS
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    /*funcional*/
    @PUT
    @Consumes("application/json")
    @Path("Cliente/AlterarCliente")
    public boolean Alterar(String content) {
        Gson g = new Gson();
        ClienteBeans clienteBeans = (ClienteBeans) g.fromJson(content, ClienteBeans.class);
        ClienteDao clienteDao = new ClienteDao();
        System.out.println("content: \n" + content);
        return clienteDao.Atualizar(clienteBeans);
    }

    @DELETE
    @Consumes({"application/json"})
    @Path("Cliente/Excluir/{idCliente}")
    public boolean Excluir(@PathParam("idCliente") int idCliente) {
            ClienteBeans clienteBeans = new ClienteBeans();
            System.out.println("Deletar cliente: " + idCliente);
            clienteBeans.setIdCliente(idCliente);

            ClienteDao clienteDao = new ClienteDao();
            clienteDao.buscar(clienteBeans);

            return clienteDao.Excluir(clienteBeans);
        }
    }

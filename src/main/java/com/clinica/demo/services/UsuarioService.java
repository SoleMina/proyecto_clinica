package com.clinica.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.clinica.demo.models.Administrador;
import com.clinica.demo.models.Especialidad;
import com.clinica.demo.models.Medico;
import com.clinica.demo.models.Usuario;
import com.clinica.demo.repositories.IEspecialidadRepository;
import com.clinica.demo.repositories.IUsuarioRepository;

@Service
public class UsuarioService {

    private final IEspecialidadRepository iEspecialidadRepository;
	
	@Autowired
	private IUsuarioRepository repoUsua;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 @Autowired
	 private IEspecialidadRepository repoEspe;

    UsuarioService(IEspecialidadRepository iEspecialidadRepository) {
        this.iEspecialidadRepository = iEspecialidadRepository;
    }
   
    public List<Usuario> listarTodos() {
		return repoUsua.findAll();
	}
    
    public Usuario obtenerPorId(int id) {
    	return repoUsua.findById(id).orElse(null);
    }

    public Usuario registrarUsuario(Usuario data) {
        if (data.getCorreo() == null || repoUsua.findByCorreo(data.getCorreo()).isPresent()) {
            throw new IllegalArgumentException("Correo ya registrado o inválido.");
        }

        Usuario nuevoUsuario;

        if ("MEDICO".equalsIgnoreCase(data.getRol_usua())) {
            Medico medico = new Medico();
            medico.setNom_usua(data.getNom_usua());
            medico.setFono_usua(data.getFono_usua());
            medico.setCorreo(data.getCorreo());
            medico.setFna_usua(data.getFna_usua());
            medico.setRol_usua("MEDICO");
            
            if (data.getId_espe() != null) {
                Especialidad especialidad = repoEspe.findById(data.getId_espe())
                        .orElseThrow(() -> new IllegalArgumentException("Especialidad no encontrada"));
                
                medico.setEspecialidad(especialidad);
                medico.setId_espe(data.getId_espe());
            } else {
                throw new IllegalArgumentException("Para registrar un médico se requiere una especialidad");
            }
            
            nuevoUsuario = medico;
        } else if ("ADMINISTRADOR".equalsIgnoreCase(data.getRol_usua())) {
            Administrador admin = new Administrador();
            admin.setNom_usua(data.getNom_usua());
            admin.setFono_usua(data.getFono_usua());
            admin.setCorreo(data.getCorreo());
            admin.setFna_usua(data.getFna_usua());
            admin.setRol_usua("ADMINISTRADOR");
            nuevoUsuario = admin;
        } else {
            Usuario paciente = new Usuario();
            paciente.setNom_usua(data.getNom_usua());
            paciente.setFono_usua(data.getFono_usua());
            paciente.setCorreo(data.getCorreo());
            paciente.setFna_usua(data.getFna_usua());
            paciente.setRol_usua("PACIENTE");
            nuevoUsuario = paciente;
        }

        String claveCodificada = passwordEncoder.encode(data.getPass_usua());
        nuevoUsuario.setPass_usua(claveCodificada);

        return repoUsua.save(nuevoUsuario);
    }





}

package com.clinica.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clinica.demo.dtos.MedicoRegisterDTO;
import com.clinica.demo.dtos.UsuarioRegisterDTO;
import com.clinica.demo.models.Administrador;
import com.clinica.demo.models.Medico;
import com.clinica.demo.models.Paciente;
import com.clinica.demo.models.Usuario;
import com.clinica.demo.repositories.IEspecialidadRepository;
import com.clinica.demo.repositories.IUsuarioRepository;

@Service
public class UsuarioService {

    private final IEspecialidadRepository IEspecialidadRepository;
	
	@Autowired
	private IUsuarioRepository repoUsua;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 @Autowired
	 private IEspecialidadRepository repoEspe;

    UsuarioService(IEspecialidadRepository IEspecialidadRepository) {
        this.IEspecialidadRepository = IEspecialidadRepository;
    }
    
    
    public List<Usuario> listarTodos() {
		return repoUsua.findAll();
	}

	/*
	 * public Usuario registrarUsuario(Usuario usuario) { // codifica la contraseña
	 * antes de guardarla String claveCodificada =
	 * passwordEncoder.encode(usuario.getPass_usua());
	 * usuario.setPass_usua(claveCodificada); return repoUsua.save(usuario); }
	 */
	
	
    private Usuario registrarUsuario(Usuario usuario) {
    	if (usuario.getRol_usua() == null) {
            usuario.setRol_usua("PACIENTE");
        }
        String claveCodificada = passwordEncoder.encode(usuario.getPass_usua());
        usuario.setPass_usua(claveCodificada);
        return repoUsua.save(usuario);
    }

    public Usuario registrarUsuario(UsuarioRegisterDTO dto) {
        String rol = dto.getRol_usua().toUpperCase();
        
        System.out.println("DTO rol: " + rol);

        switch (rol) {
            case "PACIENTE":
                return registrarPaciente(dto);
            case "ADMINISTRADOR":
                return registrarAdministrador(dto);
            case "MEDICO":
                if (dto instanceof MedicoRegisterDTO) {
                    return registrarMedico((MedicoRegisterDTO) dto);
                } else {
                    throw new IllegalArgumentException("MedicoRegisterDTO expected for MEDICO role");
                }
            default:
                throw new IllegalArgumentException("Invalid role: " + rol);
        }
    }

    private Usuario registrarPaciente(UsuarioRegisterDTO dto) {
        Usuario paciente = new Paciente();
        paciente.setNom_usua(dto.getNom_usua());
        paciente.setCorreo(dto.getEmail_usua());
        paciente.setPass_usua(dto.getPass_usua());
        paciente.setFono_usua(dto.getFono_usua());
        paciente.setFna_usua(dto.getFna_usua());
        paciente.setRol_usua("PACIENTE");

        return registrarUsuario(paciente);
    }

    private Usuario registrarAdministrador(UsuarioRegisterDTO dto) {
        Usuario admin = new Administrador();
        admin.setNom_usua(dto.getNom_usua());
        admin.setCorreo(dto.getEmail_usua());
        admin.setPass_usua(dto.getPass_usua());
        admin.setFono_usua(dto.getFono_usua());
        admin.setFna_usua(dto.getFna_usua());
        admin.setRol_usua("ADMINISTRADOR");

        return registrarUsuario(admin);
    }

    private Usuario registrarMedico(MedicoRegisterDTO dto) {
        Medico medico = new Medico();
        medico.setNom_usua(dto.getNom_usua());
        medico.setCorreo(dto.getEmail_usua());
        medico.setPass_usua(dto.getPass_usua());
        medico.setFono_usua(dto.getFono_usua());
        medico.setFna_usua(dto.getFna_usua());
        medico.setRol_usua("MEDICO");
        medico.setId_espe(dto.getId_espe());

        repoEspe.findById(dto.getId_espe()).ifPresent(medico::setEspecialidad);

        return registrarUsuario(medico);
    }
}

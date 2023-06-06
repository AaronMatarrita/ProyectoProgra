package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;


import data.CRUD;
import data.LogicXMLUser;
import domain.User;
import domain.Brand;
import domain.AirplaneModel;
import presentation.GUIAdmin;
import presentation.UserFrame;
import presentation.BrandFrame;
import presentation.ModelFrame;

public class ControllerAdmin implements ActionListener, WindowListener {

	// Declaración de instancias de clases y variables
	private GUIAdmin guiA;
	private UserFrame uF;
	private BrandFrame bF;
	private ModelFrame mF;
	private CRUD crud;
	private User Us;
	private Brand Br;
	private AirplaneModel Am;
	
	private LogicXMLUser lXMLU;

	public ControllerAdmin() {
		// Inicializo Instancias
		guiA = new GUIAdmin();
		crud = new CRUD();
		lXMLU = new LogicXMLUser();
		initializerAction();
	}

	public void initializerAction() {
		guiA.getBUsers().addActionListener(this);
		guiA.getBBrands().addActionListener(this);
		guiA.getBModels().addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		//USER FRAME*************************
//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if (guiA.getBUsers() == e.getSource()) {
			uF = new UserFrame();
			uF.addWindowListener(this);
			uF.getBAddUser().addActionListener(this);
			uF.getBUpdate().addActionListener(this);
			uF.getBClear().addActionListener(this);
			guiA.dispose();
		}
//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		if (uF.getBAddUser() == e.getSource()) {
			String user = uF.getTUser().getText();

			if (user.isEmpty() || uF.getTPassword().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
				return;
			} else if (lXMLU.isAlreadyUser(user, "Users.xml")) {
				JOptionPane.showMessageDialog(null, "El usuario ya existe");
				return;
			} else if (user.equals("admin")) {
				JOptionPane.showMessageDialog(null, "No se puede agregar un usuario con el nombre de administrador por defecto");
				return;
			}

			String password = uF.getTPassword().getText();
			String StringUserType = (String) uF.getCBUserType().getSelectedItem();
			String StringUserStatus = (String) uF.getCBUserStatus().getSelectedItem();

			if (StringUserType.equals("Indefinido") ) {
				JOptionPane.showMessageDialog(null, "Por favor, seleccione un tipo de usuario");
				return;
			}else if(StringUserStatus.equals("Indefinido")) {
				JOptionPane.showMessageDialog(null, "Por favor, seleccione el estado del usuario");
				return;
			}

			//condición ? valorSiVerdadero : valorSiFalso
			int UserType = StringUserType.equals("Administrador") ? 1 : 2;
			boolean UserStatus = StringUserStatus.equals("Activo");

			uF.clean();
			Us = new User(user, password, UserType, UserStatus);
			crud.addObject("Users.xml", "person", Us.getDataName(), Us.getData());

			JOptionPane.showMessageDialog(null, "Usuario agregado");
//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		} else if (uF.getBUpdate() == e.getSource()) {
		 //En proceso...
//------------------------------------------------------------------------------------------------------------------------------------------------------------------
		}else if (uF.getBClear() == e.getSource()) {
			String user = uF.getTUser().getText();
			if (user.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, complete el nombre del usuario a eliminar");return;
			}else if(!lXMLU.isAlreadyUser(user, "Users.xml")) {
				JOptionPane.showMessageDialog(null, "No se puede eliminar debido a que no existe");return;}
			else if(user.equals("admin")) {
				JOptionPane.showMessageDialog(null, "No se puede eliminar el usuario por defecto");return;}
			else {
				crud.deleteObject("Users.xml", "person", "user", user);
			}
			JOptionPane.showMessageDialog(null, "Usuario eliminado");
		}
		
		
		
		
		/*
		//BRAND FRAME*************************
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------
				if (guiA.getBBrands() == e.getSource()) {
					bF = new BrandFrame();
					bF.addWindowListener(this);
					bF.getBAddBrand().addActionListener(this);
					bF.getBUpdate().addActionListener(this);
					bF.getBClear().addActionListener(this);
					guiA.dispose();
				}
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------
				if (bF.getBAddBrand() == e.getSource()) {
					String brand = bF.getTBrand().getText();

					if (brand.isEmpty() || bF.getTBrand().getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
						return;
					} else if (lXMLU.isAlreadyUser(brand, "Brands.xml")) {
						JOptionPane.showMessageDialog(null, "La Marca ya existe");
						return;
					} 
					//No pueden existir marcas con el mismo nombre y no pueden eliminarse marcas que hayan 
					//sido asociados con algún modelo de avión

					bF.clean();
					Br = new Brand(brand);
					crud.addObject("Brands.xml", "person", Br.getDataName(), Br.getData());

					JOptionPane.showMessageDialog(null, "Marca agregada");
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------
				} else if (bF.getBUpdate() == e.getSource()) {
				 //En proceso...
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------
				}else if (bF.getBClear() == e.getSource()) {
					String brand = bF.getTBrand().getText();
					if (brand.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Por favor, complete el nombre de la marca a eliminar");return;
					}else if(!lXMLU.isAlreadyUser(brand, "Brands.xml")) {
						JOptionPane.showMessageDialog(null, "No se puede eliminar debido a que no existe");return;}
					else {
						crud.deleteObject("Brans.xml", "person", "brand", brand);
					}
					JOptionPane.showMessageDialog(null, "Marca eliminada");
				}*/
				
				
				
				
				
				/*
				//Model FRAME*************************
				//------------------------------------------------------------------------------------------------------------------------------------------------------------------
						if (guiA.getBModels() == e.getSource()) {
							mF = new ModelFrame();
							mF.addWindowListener(this);
							mF.getBAddModel().addActionListener(this);
							mF.getBUpdate().addActionListener(this);
							mF.getBClear().addActionListener(this);
							guiA.dispose();
						}
				//------------------------------------------------------------------------------------------------------------------------------------------------------------------
						if (mF.getBAddModel() == e.getSource()) {
							String model = mF.getTName().getText();

							if (model.isEmpty() ||mF.getTCEjecutive().getText().isEmpty()||mF.getTCTurist().getText().isEmpty()||mF.getTCEco().getText().isEmpty()) {
								JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
								return;
							} else if (lXMLU.isAlreadyUser(model, "Models.xml")) {
								JOptionPane.showMessageDialog(null, "El modelo ya existe");
								return;
							}

							int SeatsEje = Integer.parseInt(mF.getTCEjecutive().getText());
							int SeatsTur = Integer.parseInt(mF.getTCTurist().getText());
							int SeatsEco = Integer.parseInt(mF.getTCEco().getText());
							String StringBrandType = (String) mF.getCBrands().getSelectedItem();
							

							if (StringBrandType.equals("Indefinido") ) {
								JOptionPane.showMessageDialog(null, "Por favor, seleccione un tipo de usuario");
								return;
							}

							mF.clean();
							Am = new AirplaneModel(model, StringBrandType, SeatsEje ,SeatsTur, SeatsEco );
							crud.addObject("Models.xml", "person", Am.getDataName(), Am.getData());

							JOptionPane.showMessageDialog(null, "Modelo agregado");
				//------------------------------------------------------------------------------------------------------------------------------------------------------------------
						} else if (mF.getBUpdate() == e.getSource()) {
						 //En proceso...
				//------------------------------------------------------------------------------------------------------------------------------------------------------------------
						}else if (mF.getBClear() == e.getSource()) {
							String model = mF.getTName().getText();
							if (model.isEmpty()) {
								JOptionPane.showMessageDialog(null, "Por favor, complete el nombre del modelo a eliminar");return;
							}else if(!lXMLU.isAlreadyUser(model, "Models.xml")) {
								JOptionPane.showMessageDialog(null, "No se puede eliminar debido a que no existe");return;}
							else {
								crud.deleteObject("Models.xml", "person", "model", model);
							}
							JOptionPane.showMessageDialog(null, "Modelo eliminado");
						}*/
		
		
		
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
	    guiA.setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}
}
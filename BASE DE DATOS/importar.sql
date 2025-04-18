-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 03-09-2022 a las 02:58:25
-- Versión del servidor: 5.7.33
-- Versión de PHP: 8.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdentradas`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `uspEliminarCliente` (`pidcliente` CHAR(10))   begin
 --   declarar variables 
     declare mensaje text;
     declare mensaje_oficial text;
        if exists (select idcliente from Tcliente where idcliente=pidcliente )then
            if not exists (select idcliente from Tventas where idcliente=pidcliente )then
              delete from
              Tcliente 
		      where idcliente=pidcliente ;
		      set mensaje ='se elimino corectamente';
		      set mensaje_oficial=mensaje;
            else
              set mensaje ='los datos de este cliente ya exiten en otra tabla. no es posible la iliminacion';
		      set mensaje_oficial=mensaje;
            end if;
        else
			set mensaje =' id del terreno no existe';
			set mensaje_oficial=mensaje;
        end if;
     select mensaje_oficial;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `uspEliminarInformacion` (`pidinfo` CHAR(6))   begin
 --   declarar variables 
     declare mensaje text;
     declare mensaje_oficial text;
        if exists (select idinfo from TinformacionG where idinfo=pidinfo )then
          if not exists(select idinfo from Tventas where idinfo=pidinfo)then
            delete from
            TinformacionG 
		    where idinfo=pidinfo ;
		    set mensaje ='se elimino corectamente';
		    set mensaje_oficial=mensaje;
          else
             set mensaje ='los datos de este cliente ya exiten en otra tabla. no es posible la iliminacion';
			 set mensaje_oficial=mensaje;
          end if;
         
        else
			set mensaje =' id de la informacion no existe';
			set mensaje_oficial=mensaje;
        end if;
     select mensaje_oficial;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `uspEliminarPrecio` (`pidprecio` CHAR(6))   begin
 --   declarar variables 
     declare mensaje text;
     declare mensaje_oficial text;
        if exists (select idprecio from Tprecio where idprecio=pidprecio )then
           if not exists (select idprecio from Tventas where idprecio=pidprecio)then
			  delete from
              Tprecio 
		      where idprecio=pidprecio ;
		      set mensaje ='se elimino corectamente';
		     set mensaje_oficial=mensaje;
           else
              set mensaje ='los datos de este cliente ya exiten en otra tabla. no es posible la iliminacion';
			  set mensaje_oficial=mensaje;
           end if;
           
        else
			set mensaje =' id del precio no existe';
			set mensaje_oficial=mensaje;
        end if;
     select mensaje_oficial;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `uspEliminarRegistroUsuario` (`pidUsuario` VARCHAR(8))   begin
 --   declarar variables 
     declare mensaje varchar (100);
    declare mensaje_oficial varchar (100);

        if exists (select idUsuario from TUsuario where idUsuario=pidUsuario )then
          if not exists(select idUsuario from Tventas where idUsuario=pidUsuario)then
		     delete from TUsuario 
             where idUsuario=pidUsuario;
             set mensaje ='se elimino corectamente';
             set mensaje_oficial=mensaje;
          else
             set mensaje ='los datos de este usuario ya exiten en otra tabla. no es posible la iliminacion';
             set mensaje_oficial=mensaje;
          end if;
        else
		  set mensaje ='el id del usuario no existe';
		  set mensaje_oficial=mensaje;
        end if;
     select mensaje_oficial;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `uspEliminarVenta` (`pidVenta` VARCHAR(10))   begin
 --   declarar variables 
     declare mensaje varchar (100);
    declare mensaje_oficial varchar (100);

        if exists (select idventas from tventas where idventas=pidVenta )then
		     delete from tventas 
             where idventas=pidVenta;
             set mensaje ='se elimino corectamente';
             set mensaje_oficial=mensaje;
        else
		  set mensaje ='el id de la venta no existe';
		  set mensaje_oficial=mensaje;
        end if;
     select mensaje_oficial;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `uspInsertarCliente` (`pdni` VARCHAR(8), `ptipovisitante` VARCHAR(15))   begin
     --   declarar variables 
     declare mensaje text;
     declare mensaje_oficial text;
     declare codCliente char (10);
     set codCliente=fn_generar_codigo_cliente();   
	begin 
            if exists (select idcliente from Tcliente where idcliente=codCliente)then 
					 set mensaje ='el id del cliente ya existe';
                     set mensaje_oficial=mensaje;
		   else 
        
               if not exists(select * from Tcliente where dni=pdni )then
                    insert into Tcliente values(codCliente,pdni,ptipovisitante);
					set mensaje ='se inserto corectamente';
                    set mensaje_oficial=mensaje;
               else
                   set mensaje ='el cliente ya existe no es  nesario insertar otro';
                    set mensaje_oficial=mensaje;
               end if;
            end if ;
   end;
      select mensaje_oficial;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `uspInsertarDatosDeBorrados` (`pnombre` VARCHAR(20), `pdni` VARCHAR(8), `ptotalVentas` INT, `pgananciaTotal` DOUBLE)   begin
     --   declarar variables 
     declare mensaje text;
     declare mensaje_oficial text;
     declare fecha datetime;
     declare codigo int;
    set fecha=(select now());
    
    if exists (select * from TVentasBorrados )then 
	set codigo=(select max(idBorrados)from TVentasBorrados)+1;
    else
    set codigo=1;
    end if;
	begin 
           insert into TVentasBorrados values(codigo,pnombre,pdni,ptotalVentas,pgananciaTotal,fecha);
			set mensaje ='el proceso fue ejecutado correctamente';
			set mensaje_oficial=mensaje;
   end;
      select mensaje_oficial;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `uspInsertarInfo` (`prazonsocial` VARCHAR(50), `pruc` VARCHAR(11), `pareaE` VARCHAR(50), `pnombrenegocio` VARCHAR(50), `pdireccion` VARCHAR(50), `pcaja` VARCHAR(10), `pfoto` TEXT)   begin
     --   declarar variables 
     declare mensaje text;
     declare mensaje_oficial text;
     declare codinfo char (6);
     set codinfo=fn_generar_codigo_informacion();   
	begin 
            if exists (select idinfo from TinformacionG where idinfo=codinfo)then 
					 set mensaje ='el id de la informacion generar ya existe';
                     set mensaje_oficial=mensaje;
		   else 
               if not exists(select * from TinformacionG where razonsocial=prazonsocial and ruc=pruc)then
                    insert into TinformacionG values(codinfo,prazonsocial,pruc,pareaE,pnombrenegocio,pdireccion,pcaja,pfoto);
					set mensaje ='se inserto corectamente';
                    set mensaje_oficial=mensaje;
               else
                   set mensaje ='ya existe la informacion general inserte otro';
                    set mensaje_oficial=mensaje;
               end if;
            end if ;
   end;
      select mensaje_oficial;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `uspInsertarPrecio` (`pprecioadulto` DOUBLE, `pprecioinfaltil` DOUBLE, `pnombre_precio` VARCHAR(20))   begin
     --   declarar variables 
     declare mensaje text;
     declare mensaje_oficial text;
     declare codPrecio char (6);
     set codPrecio=fn_generar_codigo_precio();   
	begin 
            if exists (select idprecio from Tprecio where idprecio=codPrecio)then 
					 set mensaje ='el id del precio ya existe';
                     set mensaje_oficial=mensaje;
		   else 
               if not exists(select * from Tprecio where precioadulto=pprecioadulto and precioinfaltil=pprecioinfaltil)then
                    if not exists(select * from Tprecio where nombre_precio=pnombre_precio)then
                        insert into Tprecio values(codPrecio,pprecioadulto,pprecioinfaltil,pnombre_precio);
					    set mensaje ='se inserto corectamente';
                        set mensaje_oficial=mensaje;
                    else
                       set mensaje ='el nombre del precio ya existe inserte otro';
				      set mensaje_oficial=mensaje;
                   end if;
               else
                   set mensaje ='ya existe un precio con esos datos';
				   set mensaje_oficial=mensaje;
               end if;
            end if ;
   end;
      select mensaje_oficial;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `uspInsertarRegistroUsuario` (`ptipoUsuario` VARCHAR(15), `pcorreo` VARCHAR(50), `pclave` VARCHAR(20), `pnombre` VARCHAR(20), `papellidopaterno` VARCHAR(20), `papellidomaterno` VARCHAR(20), `pdni` VARCHAR(8), `ptelefono` VARCHAR(12), `pfoto` TEXT)   begin
     --   declarar variables 
     declare mensaje varchar (50);
     declare mensaje_oficial varchar (50);

     declare codigo char (6);
     set  codigo=fn_generar_codigo_usuario();
		if not exists (select * from TUsuario where idUsuario=codigo )then 
             if not exists (select * from TUsuario where correo=pcorreo and nombre=pnombre and dni=pdni)then 
             	insert into Tusuario values(codigo,ptipoUsuario,pcorreo,pclave,pnombre,papellidopaterno,papellidomaterno,pdni,ptelefono,pfoto);
			    set mensaje ='se inserto corectamente';
                set mensaje_oficial=mensaje;
			else
                set mensaje ='el correo, nombre o dni del usuario ya existe inserte otro';	
                set mensaje_oficial=mensaje;
			end if;
		else 
			    set mensaje ='ya existe un usuario con esa ID';
                set mensaje_oficial=mensaje;
            end if ;
      select mensaje_oficial;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `uspListarCliente` (`pDato` VARCHAR(50))   begin
select * from Tcliente
 where idcliente like concat(pDato,'%') or dni like concat(pDato,'%')or tipovisitante like concat(pDato,'%')
 order by(idcliente)asc;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `uspListarInfomacion` (`pDato` VARCHAR(50))   begin
select * from TinformacionG
 where idinfo like concat(pDato,'%') or razonsocial like concat(pDato,'%')or ruc like concat(pDato,'%')
 or areaE like concat(pDato,'%')or nombrenegocio like concat(pDato,'%')or direccion like concat(pDato,'%')
 order by(idinfo)asc;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `uspListarPrecio` (`pDato` VARCHAR(50))   begin
select * from Tprecio
 where idprecio like concat(pDato,'%') or nombre_precio like concat(pDato,'%')
 order by(idprecio)asc;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `uspListarRegistroUsuario` (`pDato` VARCHAR(50))   begin
select * from TUsuario 
where idUsuario like concat(pDato,'%') or nombre like concat(pDato,'%')
or apellidopaterno like concat(pDato,'%')or apellidomaterno like concat(pDato,'%')
or dni like concat(pDato,'%')
order by(idUsuario)asc;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `uspModificarCliente` (`pidcliente` CHAR(10), `pdni` VARCHAR(8), `ptipovisitante` VARCHAR(15))   begin
 --   declarar variables 
     declare mensaje text;
	declare mensaje_oficial text;
   if exists (select idcliente from Tcliente where idcliente=pidcliente )then
       if not exists(select * from Tcliente where dni=pdni and  tipovisitante=ptipovisitante)then
           update 
           Tcliente
           set  dni=pdni,tipovisitante=ptipovisitante 
           where idcliente=pidcliente;
           set mensaje ='se modifico corectamente';
		   set mensaje_oficial=mensaje;
         else
			set mensaje ='ya existe un cliente con esos datos ';
			set mensaje_oficial=mensaje;
		end if;                  
     else 
           set mensaje ='el codigo del cliente no existe ';
		   set mensaje_oficial=mensaje;
     end if;
     select mensaje_oficial;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `uspModificarInformacio` (`pidinfo` CHAR(6), `prazonsocial` VARCHAR(50), `pruc` VARCHAR(11), `pareaE` VARCHAR(50), `pnombrenegocio` VARCHAR(50), `pdireccion` VARCHAR(50), `pcaja` VARCHAR(10), `pfoto` TEXT)   begin
 --   declarar variables 
     declare mensaje text;
	declare mensaje_oficial text;
   if exists (select idinfo from TinformacionG where idinfo=pidinfo )then
       if not exists(select * from TinformacionG where razonsocial=prazonsocial and  ruc=pruc)then
           update 
           TinformacionG
           set  razonsocial=prazonsocial,ruc=pruc,areaE=pareaE,nombrenegocio=pnombrenegocio,direccion=pdireccion,caja=pcaja,foto=foto
           where idinfo=pidinfo;
           set mensaje ='se modifico corectamente';
		   set mensaje_oficial=mensaje;
         else
            update 
            TinformacionG
            set  areaE=pareaE,nombrenegocio=pnombrenegocio,direccion=pdireccion,caja=pcaja,foto=foto
			where idinfo=pidinfo;
			set mensaje ='ya existe una informacion con esos datos (razon social y ruc)se omitiran estos campos';
			set mensaje_oficial=mensaje;
		end if;                  
     else 
           set mensaje ='el codigo de la informacio no existe ';
		   set mensaje_oficial=mensaje;
     end if;
     select mensaje_oficial;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `uspModificarPrecio` (`pidprecio` CHAR(6), `pprecioadulto` DOUBLE, `pprecioinfaltil` DOUBLE, `pnombre_precio` VARCHAR(20))   begin
 --   declarar variables 
     declare mensaje text;
	declare mensaje_oficial text;
   if exists (select idprecio from Tprecio where idprecio=pidprecio )then
       if not exists(select * from Tprecio where precioadulto=pprecioadulto and  precioinfaltil=pprecioinfaltil)then
            if not exists(select * from Tprecio where nombre_precio=pnombre_precio)then
                 update 
                 Tprecio
                 set  precioadulto=pprecioadulto,precioinfaltil=pprecioinfaltil,nombre_precio=pnombre_precio
                 where idprecio=pidprecio;
                 set mensaje ='se modifico corectamente';
		         set mensaje_oficial=mensaje;
	         else
				 set mensaje ='el nombre del precio ya existe inserte otro';
				 set mensaje_oficial=mensaje;
		     end if;
         else
			set mensaje ='ya existe un precio con esos datos ';
			set mensaje_oficial=mensaje;
		end if;                  
     else 
           set mensaje ='el codigo del precio no existe ';
		   set mensaje_oficial=mensaje;
     end if;
     select mensaje_oficial;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `uspModificarRegistroUsuario` (`pidUsuario` CHAR(6), `ptipoUsuario` VARCHAR(15), `pcorreo` VARCHAR(50), `pclave` VARCHAR(20), `pnombre` VARCHAR(20), `papellidopaterno` VARCHAR(20), `papellidomaterno` VARCHAR(20), `pdni` VARCHAR(8), `ptelefono` VARCHAR(12), `pfoto` TEXT)   begin

 --   declarar variables 
     declare mensaje varchar (50);
     declare mensaje_oficial varchar (50);
     declare filas int;
     
     set filas=(select count(*) from TUsuario where nombre=pnombre and correo=pcorreo);
     
        if exists (select idUsuario from TUsuario where idUsuario=pidUsuario)then
		   if (filas < 2)then 
               if(pfoto=null or pfoto="")then
               update 
               Tusuario
                set tipoUsuario=ptipoUsuario, correo=pcorreo , clave=pclave, nombre=pnombre,
                apellidopaterno=papellidopaterno,apellidomaterno=papellidomaterno,dni=pdni,telefono=ptelefono
                where idUsuario=pidUsuario;
                set mensaje ='se modifico corectamente';
		        set mensaje_oficial=mensaje;
                
                else
                update 
                Tusuario
                set tipoUsuario=ptipoUsuario, correo=pcorreo , clave=pclave, nombre=pnombre,
                apellidopaterno=papellidopaterno,apellidomaterno=papellidomaterno,dni=pdni,telefono=ptelefono,foto=pfoto
                where idUsuario=pidUsuario;
                set mensaje ='se modifico corectamente';
		        set mensaje_oficial=mensaje;
                
               end if; 
		   else
			   set mensaje ='ya existe el correo o nombre ingresado inserte otro';	
               set mensaje_oficial=mensaje;
		   end if;
	 else 
        set mensaje ='el id del uauario no existe';
		set mensaje_oficial=mensaje;
	end if;
        
     select mensaje_oficial;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_AnularVenta` (`pidventas` CHAR(10), `pidUsuario` CHAR(6))   begin
     --   declarar variables 
     
     declare mensaje varchar (50);
     declare mensaje_oficial varchar (50);
     declare id_usuarioVendido char (6);
    
      if exists (select idventas   from Tventas where idventas=pidventas)then
		 if exists (select idUsuario   from TUsuario where idUsuario=pidUsuario)then     
             set id_usuarioVendido=(select idUsuario from Tventas where  idventas=pidventas); 
             -- verificar que la persona quien vendio anteriormente sea la que anula su venta y no la de otra persona
             if(id_usuarioVendido=pidUsuario)then
             
                 update 
                 Tventas 
                 set estado ='ANULADO'
                 where idventas=pidventas;
	             set mensaje='la venta fue anulada';
	             set mensaje_oficial=mensaje;
             else
			    set mensaje='usted no esta autorizado para anular esta venta';
                set mensaje_oficial=mensaje;
             end if;
           else 
              set mensaje='el id  del usuario no exite';
             set mensaje_oficial=mensaje;
        end if;  
		else 
              set mensaje='el id  de la venta no existe';
             set mensaje_oficial=mensaje;
        end if;
     select mensaje_oficial;

end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_filtrar_por_usuarios` (`pDato` VARCHAR(50))   begin
select V.idUsuario,concat(U.nombre,' ',U.apellidopaterno,' ',U.apellidomaterno)as usuario,
 sum(V.montototal)as total_ganancias, count(V.idventas)as total_ventas, sum(V.totalpersonas)as total_personas
 
 from (Tventas V inner join TUsuario U on V.idUsuario=U.idUsuario)

 
 where  U.nombre   like concat(pDato,'%') or U.apellidopaterno   like concat(pDato,'%') or U.apellidomaterno   like concat(pDato,'%')
 or U.dni   like concat(pDato,'%')
 group by(U.idUsuario)asc;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_ganacias_de_hoy_usuario` (`pIdusuario` CHAR(6), `pFecha` DATE)   begin
select  sum(nradultos)as adultos_totales, sum(nrinfantil)as niños_totales,
count(idventas)as ventas_realizadas, sum(montototal)as ganancias_totales
 
 from Tventas
 where idUsuario=pIdusuario and YEAR(fecha)=YEAR(pFecha) and MONTH(fecha)=MONTH(pFecha) and  DAY(fecha)=DAY(pFecha)  ;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_iniciar_sesion` (`pcorreo` VARCHAR(50), `pclave` VARCHAR(20))   begin 

declare acceso varchar (20);
declare pidusuario char (6);
if not exists(select * from TUsuario)then
  set acceso='acceso vacio';
  set pidusuario='';
else
  if exists (select * from TUsuario where correo=pcorreo and clave= pclave)then
set acceso='acceso permitido';
set pidusuario=(select idUsuario from TUsuario where correo=pcorreo and clave= pclave);
else 
  
set acceso='acceso denegado';
set pidusuario='';
end if;
end if;


select acceso,pidusuario;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_Insertar_venta` (`pnradultos` INT, `pnrinfantil` INT, `pdni` VARCHAR(8), `ptipovisitante` VARCHAR(15), `pidUsuario` CHAR(6), `pidinfo` CHAR(6), `pidprecio` CHAR(6))   begin
     --   declarar variables 
     
     declare mensaje varchar (50);
	 declare codigo_venta char (10);
     declare mensaje_oficial varchar (50);
     declare pfecha_Vendida datetime;
     declare ptotalpersonas int;
     declare pmontototal double;
	 declare pprecioadulto double;
	 declare pprecioinfaltil double;
     
	 declare pid_cliente char(6);
     set  pid_cliente=fn_reuperar_id_cliente(pdni,ptipovisitante);
     
     set pfecha_Vendida=(select now());
     set ptotalpersonas=(pnradultos + pnrinfantil);
     set pprecioadulto=(select precioadulto from Tprecio where idprecio=pidprecio);
     set pprecioinfaltil=(select precioinfaltil from Tprecio where idprecio=pidprecio);
     
     set pmontototal=(pnradultos*pprecioadulto)+(pnrinfantil*pprecioinfaltil);
    
     
     set codigo_venta=fn_generar_codigo_venta();
      if not exists (select idventas   from Tventas where idventas=codigo_venta)then
		     if exists (select idUsuario from TUsuario where idUsuario=pidUsuario )then
                 if exists (select idcliente    from Tcliente where idcliente=pid_cliente )then
                    if exists (select idinfo from TinformacionG where idinfo=pidinfo )then
                         if exists (select idprecio from Tprecio where idprecio=pidprecio )then
                         
								insert into Tventas values (codigo_venta,pnradultos,pnrinfantil,pfecha_Vendida,
                                ptotalpersonas,pmontototal,'VENDIDO',pidUsuario,pid_cliente,pidinfo,pidprecio);
                                
						        set mensaje='la venta se hizo con exito';
                                set mensaje_oficial=mensaje;
					     else 
						    set mensaje='la id del precio no exite';
						    set mensaje_oficial=mensaje;
				         end if;
					else 
						set mensaje='el id de la imformacion general no existe';
						set mensaje_oficial=mensaje;
				    end if;
                  else 
                    set mensaje='el id del cliente no existe';
					set mensaje_oficial=mensaje;
                    end if;
             else 
             set mensaje='el id del usuario no existe';
			 set mensaje_oficial=mensaje;
             end if;
		else 
              set mensaje='el id  de la venta no existe';
             set mensaje_oficial=mensaje;
        end if;
     select mensaje_oficial;

end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_listar_ganacia_general` (`pDato` VARCHAR(50))   begin
select V.idventas,concat(U.nombre,' ',U.apellidopaterno)as usuario, 
 V.nradultos, V.nrinfantil, V.totalpersonas, V.montototal, C.tipovisitante,V.fecha
 
 from ((Tventas V inner join TUsuario U on V.idUsuario=U.idUsuario)
 inner join Tcliente C on V.idcliente=C.idcliente)
 inner join Tprecio P on V.idprecio=P.idprecio
 
 where  U.nombre   like concat(pDato,'%') or V.fecha like concat(pDato,'%')
 order by(V.idventas)asc;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_listar_ventas` (`pDato` VARCHAR(50))   begin
select V.idventas, V.nradultos, V.nrinfantil, V.fecha, V.totalpersonas, V.montototal, V.estado, 
concat(U.nombre,' ',U.apellidopaterno,' ' ,U.apellidomaterno )as usuario, 
C.dni, C.tipovisitante, I.razonsocial, I.nombrenegocio, P.precioadulto, P.precioinfaltil



 from (((Tventas V inner join TUsuario U on V.idUsuario=U.idUsuario)
 inner join Tcliente C on V.idcliente=C.idcliente)
 inner join TinformacionG I on V.idinfo=I.idinfo)
 inner join Tprecio P on V.idprecio=P.idprecio
 
 where V.idventas   like concat(pDato,'%') or V.fecha like concat(pDato,'%') or
 U.idUsuario like concat(pDato,'%') or U.correo like concat(pDato,'%')or U.nombre like concat(pDato,'%') or U.dni like concat(pDato,'%')
 or I.idinfo like concat(pDato,'%')or I.razonsocial like concat(pDato,'%')or I.nombrenegocio like concat(pDato,'%')or I.caja like concat(pDato,'%')
 or P.idprecio like concat(pDato,'%')or p.precioadulto like concat(pDato,'%')or P.precioinfaltil like concat(pDato,'%')
 order by(V.idventas)asc;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_listar_ventas_borrar` (`pDato` VARCHAR(50))   begin
select V.idventas, V.nradultos, V.nrinfantil, V.fecha, V.totalpersonas, V.montototal,
V.idUsuario,V.idcliente
 
 from (((Tventas V inner join TUsuario U on V.idUsuario=U.idUsuario)
 inner join Tcliente C on V.idcliente=C.idcliente)
 inner join TinformacionG I on V.idinfo=I.idinfo)
 inner join Tprecio P on V.idprecio=P.idprecio
 
 
 where V.idventas   like concat(pDato,'%') or V.fecha like concat(pDato,'%') 
 order by(idventas)asc;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_listar_ventas_de_usuario` (`pIdusuario` VARCHAR(50))   begin
select U.idUsuario,concat(U.nombre,' ',U.apellidopaterno,' ',U.apellidomaterno)as usuario, 
	U.dni,U.correo,U.telefono,
 sum(V.montototal)as total_ganancias, count(V.idventas)as total_ventas, 
 sum(V.nradultos)as total_adultos,sum(V.nrinfantil)as total_niños,sum(V.totalpersonas)as total_personas,
 -- clientes locales que vendio el usuario
 (select sum(V.totalpersonas) from Tventas V inner join  Tcliente C on V.idcliente=C.idcliente  where C.tipovisitante='Local' and V.idUsuario=pIdusuario)as clientes_locales,
 -- clientes externos que vendio el usuario
(select sum(V.totalpersonas) from Tventas V inner join  Tcliente C on V.idcliente=C.idcliente  where C.tipovisitante='Externo' and V.idUsuario=pIdusuario)as clientes_externos,
(select sum(V.totalpersonas) from Tventas V inner join  Tcliente C on V.idcliente=C.idcliente  where C.tipovisitante='Extranjero' and V.idUsuario=pIdusuario)as clientes_extranjero
 
 
 
 from ((Tventas V inner join TUsuario U on V.idUsuario=U.idUsuario)
 inner join Tcliente C on V.idcliente=C.idcliente)
 inner join Tprecio P on V.idprecio=P.idprecio
 
 where  V.idUsuario=pIdusuario
 order by(V.idUsuario)asc;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_listar__por_año` (`pFecha_inicio` DATE, `pfecha_fin` DATE)   begin
select concat(U.nombre,' ',U.apellidopaterno)as usuario, 
 V.nradultos, V.nrinfantil, V.totalpersonas, V.montototal, C.tipovisitante,V.fecha
 
 from ((Tventas V inner join TUsuario U on V.idUsuario=U.idUsuario)
 inner join Tcliente C on V.idcliente=C.idcliente)
 inner join Tprecio P on V.idprecio=P.idprecio
 
 where CAST(V.fecha as Date) between pFecha_inicio and pfecha_fin  

 order by(V.idventas)asc;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_listar__por_fecha` (`pFecha` DATE)   begin
select concat(U.nombre,' ',U.apellidopaterno)as usuario, 
 V.nradultos, V.nrinfantil, V.totalpersonas, V.montototal, C.tipovisitante,V.fecha
 
 from ((Tventas V inner join TUsuario U on V.idUsuario=U.idUsuario)
 inner join Tcliente C on V.idcliente=C.idcliente)
 inner join Tprecio P on V.idprecio=P.idprecio
 
 where  YEAR(V.fecha)=YEAR(pFecha) and MONTH(V.fecha)=MONTH(pFecha) and  DAY(V.fecha)=DAY(pFecha)
 order by(V.idventas)asc;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_venta_borrar_entre_fechas` (`pFecha_inicio` DATE, `pfecha_fin` DATE)   begin
select *
 from Tventas
 where CAST(fecha as Date) between pFecha_inicio and pfecha_fin  

 order by(idventas)asc;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_venta_borrar_fecha` (`pFecha` DATE)   begin
select V.idventas, V.nradultos, V.nrinfantil,  V.montototal,V.fecha,
V.idcliente
 
 from (((Tventas V inner join TUsuario U on V.idUsuario=U.idUsuario)
 inner join Tcliente C on V.idcliente=C.idcliente)
 inner join TinformacionG I on V.idinfo=I.idinfo)
 inner join Tprecio P on V.idprecio=P.idprecio

 where  YEAR(V.fecha)=YEAR(pFecha) and MONTH(V.fecha)=MONTH(pFecha) and  DAY(V.fecha)=DAY(pFecha)
 order by(idventas)asc;
end$$

--
-- Funciones
--
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_generar_codigo_cliente` () RETURNS CHAR(6) CHARSET latin1  begin 
declare maximo char(6); 
declare codigo char (6); 
declare aux char (5);

if exists (select * from Tcliente )then 
set maximo=(select max(idcliente)from Tcliente);
set aux=right(maximo,5)+1;
set codigo=concat('C',right(concat('00000',aux),5));
else 
set codigo=concat('C','00001');
end if;
return codigo;
end$$

CREATE DEFINER=`root`@`localhost` FUNCTION `fn_generar_codigo_informacion` () RETURNS CHAR(6) CHARSET latin1  begin 
declare maximo char(6); 
declare codigo char (6); 
declare aux char (5);

if exists (select * from TinformacionG )then 
set maximo=(select max(idinfo)from TinformacionG);
set aux=right(maximo,5)+1;
set codigo=concat('I',right(concat('00000',aux),5));
else 
set codigo=concat('I','00001');
end if;
return codigo;
end$$

CREATE DEFINER=`root`@`localhost` FUNCTION `fn_generar_codigo_precio` () RETURNS CHAR(6) CHARSET latin1  begin 
declare maximo char(6); 
declare codigo char (6); 
declare aux char (5);

if exists (select * from Tprecio )then 
set maximo=(select max(idprecio)from Tprecio);
set aux=right(maximo,5)+1;
set codigo=concat('P',right(concat('00000',aux),5));
else 
set codigo=concat('P','00001');
end if;
return codigo;
end$$

CREATE DEFINER=`root`@`localhost` FUNCTION `fn_generar_codigo_usuario` () RETURNS CHAR(6) CHARSET latin1  begin 
declare maximo char(6); 
declare codigo char (6); 
declare aux char (5);

if exists (select * from TUsuario )then 
set maximo=(select max(idUsuario)from TUsuario);
set aux=right(maximo,5)+1;
set codigo=concat('U',right(concat('00000',aux),5));
else 
set codigo=concat('U','00001');
end if;
return codigo;
end$$

CREATE DEFINER=`root`@`localhost` FUNCTION `fn_generar_codigo_venta` () RETURNS CHAR(10) CHARSET latin1  begin 

declare maximo char(10);
declare codigo char (10);
declare aux char (10);

if exists (select * from Tventas )then 
set maximo=(select max(idventas)from Tventas);
set aux=(right(maximo,9)+1);
set codigo=concat('V',right(concat('000000000',aux),9));
else 
set codigo='V000000001';
end if;
return codigo;
end$$

CREATE DEFINER=`root`@`localhost` FUNCTION `fn_reuperar_id_cliente` (`pdni` VARCHAR(8), `tipovisitante` VARCHAR(15)) RETURNS CHAR(10) CHARSET latin1  begin 

declare id_cliente char (10); 
declare codigo char (10);

  if  (pdni='')then
	  set id_cliente=fn_generar_codigo_cliente();
      insert into Tcliente values(id_cliente,'SIN DNI',tipovisitante);
      set codigo=id_cliente;
      
      else
       if exists (select idcliente   from Tcliente where dni=pdni)then
   
       set codigo=(select idcliente   from Tcliente where dni=pdni);
      end if;
      
      if not exists (select idcliente   from Tcliente where dni=pdni)then
       set id_cliente=fn_generar_codigo_cliente();
       insert into Tcliente values(id_cliente,pdni,tipovisitante);
        set codigo=id_cliente;
      end if;
   end if;  
   

return codigo;
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tcliente`
--

CREATE TABLE `tcliente` (
  `idcliente` char(10) NOT NULL,
  `dni` varchar(8) DEFAULT NULL,
  `tipovisitante` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tinformaciong`
--

CREATE TABLE `tinformaciong` (
  `idinfo` char(6) NOT NULL,
  `razonsocial` varchar(50) DEFAULT NULL,
  `ruc` varchar(11) DEFAULT NULL,
  `areaE` varchar(50) DEFAULT NULL,
  `nombrenegocio` varchar(50) DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `caja` varchar(10) DEFAULT NULL,
  `foto` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tprecio`
--

CREATE TABLE `tprecio` (
  `idprecio` char(6) NOT NULL,
  `precioadulto` double DEFAULT NULL,
  `precioinfaltil` double DEFAULT NULL,
  `nombre_precio` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tusuario`
--

CREATE TABLE `tusuario` (
  `idUsuario` char(6) NOT NULL,
  `tipoUsuario` varchar(15) DEFAULT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `clave` varchar(20) DEFAULT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `apellidopaterno` varchar(20) DEFAULT NULL,
  `apellidomaterno` varchar(20) DEFAULT NULL,
  `dni` varchar(8) DEFAULT NULL,
  `telefono` varchar(12) DEFAULT NULL,
  `foto` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tusuario`
--

INSERT INTO `tusuario` (`idUsuario`, `tipoUsuario`, `correo`, `clave`, `nombre`, `apellidopaterno`, `apellidomaterno`, `dni`, `telefono`, `foto`) VALUES
('U00001', 'administrador', 'medali@gmail.com', 'hack', 'medali', 'cusihuata', 'mercado', '77354961', '940500006', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tventas`
--

CREATE TABLE `tventas` (
  `idventas` char(10) NOT NULL,
  `nradultos` int(11) DEFAULT NULL,
  `nrinfantil` int(11) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `totalpersonas` int(11) DEFAULT NULL,
  `montototal` double DEFAULT NULL,
  `estado` varchar(10) DEFAULT NULL,
  `idUsuario` char(6) DEFAULT NULL,
  `idcliente` char(10) DEFAULT NULL,
  `idinfo` char(6) DEFAULT NULL,
  `idprecio` char(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tventasborrados`
--

CREATE TABLE `tventasborrados` (
  `idBorrados` int(11) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `dni` varchar(8) DEFAULT NULL,
  `totalVentas` int(11) DEFAULT NULL,
  `gananciaTotal` double DEFAULT NULL,
  `fecha` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tcliente`
--
ALTER TABLE `tcliente`
  ADD PRIMARY KEY (`idcliente`);

--
-- Indices de la tabla `tinformaciong`
--
ALTER TABLE `tinformaciong`
  ADD PRIMARY KEY (`idinfo`);

--
-- Indices de la tabla `tprecio`
--
ALTER TABLE `tprecio`
  ADD PRIMARY KEY (`idprecio`);

--
-- Indices de la tabla `tusuario`
--
ALTER TABLE `tusuario`
  ADD PRIMARY KEY (`idUsuario`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- Indices de la tabla `tventas`
--
ALTER TABLE `tventas`
  ADD PRIMARY KEY (`idventas`),
  ADD KEY `idUsuario` (`idUsuario`),
  ADD KEY `idcliente` (`idcliente`),
  ADD KEY `idinfo` (`idinfo`),
  ADD KEY `idprecio` (`idprecio`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tventas`
--
ALTER TABLE `tventas`
  ADD CONSTRAINT `tventas_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `tusuario` (`idUsuario`),
  ADD CONSTRAINT `tventas_ibfk_2` FOREIGN KEY (`idcliente`) REFERENCES `tcliente` (`idcliente`),
  ADD CONSTRAINT `tventas_ibfk_3` FOREIGN KEY (`idinfo`) REFERENCES `tinformaciong` (`idinfo`),
  ADD CONSTRAINT `tventas_ibfk_4` FOREIGN KEY (`idprecio`) REFERENCES `tprecio` (`idprecio`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

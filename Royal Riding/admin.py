from flask import* 
from database import*
import uuid

admin=Blueprint('admin',__name__)

@admin.route('/admin_home')
def admin_home():
	return render_template('admin_home.html')

@admin.route('/admin_manageplace',methods=['post','get'])
def admin_manageplace():
	data={}
	q="select * from place"
	res=select(q)
	data['place']=res
	if "addplace" in request.form:
		p=request.form['place']

		q="insert into place values(null,'%s')"%(p)
		insert(q)
		flash('successfully')
		return redirect(url_for('admin.admin_manageplace'))


	if "action" in request.args:
		action=request.args['action']
		pid=request.args['pid']

	else:
		action=None


	if action=='delete':
		q="delete from place where place_id='%s' "%(pid)
		delete(q)
		flash('successfully')
		return redirect(url_for('admin.admin_manageplace'))

	if action=='update':
		q="select * from place where place_id='%s'"%(pid)
		res=select(q)
		data['placeup']=res


	if "update" in request.form:
		p=request.form['place']

		q="update place set place='%s' where place_id='%s'"%(p,pid)
		update(q)
		flash('successfully')
		return redirect(url_for('admin.admin_manageplace'))
		
	return render_template('admin_manageplace.html',data=data)

@admin.route('/admin_manageotherplace',methods=['post','get'])
def admin_manageotherplace():
	data={}
	q="select * from place"
	res=select(q)
	data['place']=res


	q="select * from otherplace inner join place using (place_id)"
	res1=select(q)
	data['otherplace']=res1

	if "addotherplace" in request.form:
		pla=request.form['pla']
		n=request.form['name']
		d=request.form['details']

		la=request.form['lat']
		lo=request.form['lon']
		ph=request.form['phone']
		e=request.form['email']
		q="insert into otherplace values(null,'%s','%s','%s','%s','%s','%s','%s')"%(pla,n,d,la,lo,ph,e)
		insert(q)
		flash('successfully')

	if "action" in request.args:
		action=request.args['action']
		opid=request.args['opid']

	else:
		action=None

	if action=='delete':
		q="delete from otherplace where otherplace_id='%s'"%(opid)
		delete(q)
		flash('successfully')

	if action=='update':
		q="select * from otherplace inner join place using (place_id) where otherplace_id='%s'"%(opid)
		res=select(q)
		data['otherplaceup']=res


	if "update" in request.form:
		pla=request.form['pla']
		n=request.form['name']
		d=request.form['details']

		la=request.form['lat']
		lo=request.form['lon']
		ph=request.form['phone']
		e=request.form['email']
		q="update otherplace set place_id='%s',name='%s',details='%s',latitude='%s',longitude='%s',phone='%s',email='%s' where otherplace_id='%s'"%(pla,n,d,la,lo,ph,e,opid)
		update(q)
		flash('successfully')
		return redirect(url_for('admin.admin_manageotherplace'))

	return render_template('admin_manageotherplace.html',data=data)

@admin.route('/admin_manageworkshop',methods=['post','get'])
def admin_manageworkshop():
	data={}

	q="select * from workshop"
	res=select(q)
	data['workshop']=res

	if "add" in request.form:
		w=request.form['workshop']
		d=request.form['details']
		i=request.files['img']
		p=request.form['number']
		lon=request.form['lon']
		lat=request.form['lat']
		path="static/image/"+str(uuid.uuid4())+i.filename
		i.save(path)
		q="insert into workshop values(null,'%s','%s','%s','%s','%s','%s')"%(w,d,path,p,lat,lon)
		insert(q)
		flash('successfully')
		return redirect(url_for('admin.admin_manageworkshop'))


	if "action" in request.args:
		action=request.args['action']
		wid=request.args['wid']
	else:
		action=None

	if action=='delete':
		q="delete from workshop where workshop_id='%s'"%(wid)
		delete(q)
		flash('successfully')
		return redirect(url_for('admin.admin_manageworkshop'))

	if action=='update':
		q="select * from workshop where workshop_id='%s'"%(wid)
		res=select(q)
		data['workshopup']=res


	if "update" in request.form:
		w=request.form['workshop']
		d=request.form['details']
		i=request.files['img']
		p=request.form['number']
		path="static/image/"+str(uuid.uuid4())+i.filename
		i.save(path)
		q="update workshop set workshop='%s',details='%s',image='%s',phone='%s' where workshop_id='%s'"%(w,d,path,p,wid)
		update(q)
		print(q)
		flash('successfully')
		return redirect(url_for('admin.admin_manageworkshop'))
		
		
				
	return render_template('admin_manageworkshop.html',data=data)

@admin.route('/admin_viewclub')
def admin_viewclub():
	data={}
	q="select * from club inner join login using (login_id)"
	res=select(q)
	data['club']=res

	if "action" in request.args:
		action=request.args['action']
		cid=request.args['cid']

	else:
		action=None

	if action=='accept':
		q="update club set status='Approve' where login_id='%s'"%(cid)
		update(q)
		q="update login set usertype='club' where login_id='%s'"%(cid)
		update(q)
		flash('successfully')
		return redirect(url_for('admin.admin_viewclub'))

	if action=='reject':
		q="update club set status='reject' where login_id='%s'"%(cid)
		update(q)
		q="update login set usertype='reject' where login_id='%s'"%(cid)
		update(q)
		flash('successfully')
		return redirect(url_for('admin.admin_viewclub'))
				

			
		
	return render_template('admin_viewclub.html',data=data)



@admin.route('/managepackage',methods=['post','get'])
def managepackage():
	data={}
	pid=request.args['pid']
	q="select * from package where place_id='%s'"%(pid)
	res=select(q)
	data['package']=res

	if "addpackages" in request.form:
		p=request.form['packages']
		a=request.form['amount']
		d=request.form['details']
		pid=request.args['pid']

		q="insert into package values(null,'%s','%s','%s','%s')"%(pid,p,a,d)
		insert(q)
		flash('successfully')
		return redirect(url_for('admin.managepackage',pid=pid))


	if "action" in request.args:
		action=request.args['action']
		pid=request.args['pid']
		plid=request.args['plid']

	else:
		action=None


	if action=='delete':
		q="delete from package where package_id='%s' "%(pid)
		delete(q)
		flash('successfully')
		return redirect(url_for('admin.managepackage',plid=plid))

	if action=='update':
		q="select * from package where package_id='%s'"%(pid)
		res=select(q)
		data['packagesup']=res


	if "update" in request.form:
		p=request.form['packages']
		a=request.form['amount']
		d=request.form['details']

		
		q="update package set packagename='%s' ,Amount='%s' , details='%s' where package_id='%s'"%(p,a,d,pid)
		update(q)
		flash('successfully')
		return redirect(url_for('admin.managepackage',plid=plid))
		
	return render_template('managepackage.html',data=data,plid=plid)

@admin.route('/admin_managehotels',methods=['post','get'])
def admin_managehotels():
	data={}

	q="select * from hotel"
	res=select(q)
	data['hotels']=res

	if "add" in request.form:
		w=request.form['hotels']
		d=request.form['place']
		e=request.form['email']
		i=request.files['img']
		p=request.form['number']
		lon=request.form['lon']
		lat=request.form['lat']

		path="static/image/"+str(uuid.uuid4())+i.filename
		i.save(path)
		q="insert into hotel values(null,'%s','%s','%s','%s','%s','%s','%s')"%(w,p,e,path,d,lon,lat)
		insert(q)
		flash('successfully')
		return redirect(url_for('admin.admin_managehotels'))


	if "action" in request.args:
		action=request.args['action']
		hid=request.args['hid']
	else:
		action=None

	if action=='delete':
		q="delete from hotel where hotel_id='%s'"%(hid)
		delete(q)
		flash('successfully')
		return redirect(url_for('admin.admin_managehotels'))

	if action=='update':
		q="select * from hotel where hotel_id='%s'"%(hid)
		res=select(q)
		data['hotelsup']=res


	if "update" in request.form:
		w=request.form['hotels']
		d=request.form['place']
		e=request.form['email']
		i=request.files['img']
		p=request.form['number']
		path="static/image/"+str(uuid.uuid4())+i.filename
		i.save(path)
		q="update hotel set hotalname='%s',hotelrent='%s',email='%s',photo='%s',phone='%s' where hotel_id='%s'"%(w,d,e,path,p,hid)
		update(q)
		print(q)
		flash('successfully')
		return redirect(url_for('admin.admin_managehotels'))
		
		
				
	return render_template('admin_managehotels.html',data=data)

			
	
			
			
 	


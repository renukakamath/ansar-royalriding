from flask import *
from database import*

club=Blueprint('club',__name__)

@club.route('/club_home')
def club_home():

	return render_template('club_home.html')

@club.route('/club_manageevent',methods=['post','get'])
def club_manageevent():
	cid=session['club_id']
	data={}
	q="select * from event where club_id='%s'"%(cid)
	res=select(q)
	data['event']=res

	if "addevent" in request.form:
		e=request.form['event']
		p=request.form['place']
		la=request.form['lat']
		lo=request.form['lon']
		q="insert into event values(null,'%s','%s','%s','%s','%s')"%(cid,e,p,la,lo)
		insert(q)
		flash('successfully')
		return redirect(url_for('club.club_manageevent',data=data))


	if "action" in request.args:
		action=request.args['action']
		eid=request.args['eid']

	else:
		action=None

	if action=='delete':
		q="delete from event where event_id='%s'"%(eid)
		delete(q)
		flash('successfully')
		return redirect(url_for('club.club_manageevent'))


	if action=='update':
		q="select * from event where event_id='%s'"%(eid)
		res1=select(q)
		data['eventup']=res1


	if "update" in request.form:
		e=request.form['event']
		p=request.form['place']
		la=request.form['lat']
		lo=request.form['lon']
		q="update event set event='%s', place='%s',latitude='%s',longitude='%s' where event_id='%s'"%(e,p,la,lo,eid)
		update(q)
		flash('successfully')
		return redirect(url_for('club.club_manageevent'))
	
	return render_template('club_manageevent.html',data=data)

@club.route('/club_managetrips',methods=['post','get'])
def club_managetrips():
	data={}
	cid=session['club_id']

	q="select * from trip where club_id='%s'"%(cid)
	res=select(q)
	data['trip']=res

	if "addtrip" in request.form:
		t=request.form['trip']
		p=request.form['place']
		d=request.form['details']
		fd=request.form['fdate']
		td=request.form['tdate']
		a=request.form['amount']
		q="insert into trip values(null,'%s','%s','%s','%s','%s','%s','%s')"%(cid,t,p,d,fd,td,a)
		insert(q)
		flash('successfully')
		return redirect(url_for('club.club_managetrips'))


	if "action" in request.args:
		action=request.args['action']
		tid=request.args['tid']

	else:
		action=None

	if action=='delete':
		q="delete from trip where trip_id='%s'"%(tid)
		delete(q)
		flash('successfully')
		return redirect(url_for('club.club_managetrips'))

	if action=='update':
		q="select * from trip where trip_id='%s'"%(tid)
		res=select(q)
		data['tripup']=res

	if "update" in request.form:
		t=request.form['trip']
		p=request.form['place']
		d=request.form['details']
		fd=request.form['fdate']
		td=request.form['tdate']
		a=request.form['amount']
		q="update trip set trip='%s' ,place='%s',details='%s',from_date='%s',to_date='%s',amount='%s' where trip_id='%s'"%(t,p,d,fd,td,a,tid)
		update(q)
		flash('successfully')
		return redirect(url_for('club.club_managetrips'))
	
	return render_template('club_managetrips.html',data=data)

@club.route('/club_viewrequests')
def club_viewrequests():
	data={}
	q="SELECT * FROM request  INNER JOIN trip USING (trip_id) INNER JOIN USER ON user.login_id=request.user_id GROUP BY request_id"
	res=select(q)
	data['request']=res
	
	return render_template('club_viewrequests.html',data=data)

@club.route('/club_viewjoinedusers')
def club_viewjoinedusers():
	data={}
	cid=session['club_id']
	q="SELECT * FROM joinclub  INNER JOIN club USING (club_id) inner join user on user.login_id=joinclub.user_id where club_id='%s'"%(cid)
	res=select(q)
	print(q)
	data['join']=res

	return render_template('club_viewjoinedusers.html',data=data)
	
	
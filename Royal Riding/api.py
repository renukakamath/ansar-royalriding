from flask import * 
from database import* 
import uuid

api=Blueprint('api',__name__)

@api.route('/login')
def login():
	data={}
	u=request.args['username']
	p=request.args['password']
	q1="select * from login where username='%s' and `password`='%s'"%(u,p)
	print(q1)
	res=select(q1)
	if res:
		data['data']=res
		data['status']='success'
	else:
		data['status']='failed'
	return str(data)

@api.route('/Registration')
def Registration():
	data={}
	f=request.args['fname']
	l=request.args['lname']
	
	pl=request.args['place']
	
	ph=request.args['phone']
	e=request.args['email']
	l=request.args['licence']

	u=request.args['username']
	p=request.args['password']
	a=request.args['aadaar']
	q="select * from login where username='%s' and password='%s'"%(u,p)
	res=select(q)
	if res:
		data['status']='already'
	else:
		q="insert into login values(NULL,'%s','%s','User')"%(u,p)
		lid=insert(q)
		r="insert into user values(NULL,'%s','%s','%s','%s','%s','%s','%s','%s')"%(lid,f,l,pl,ph,e,l,a)
		insert(r)
		data['status']="success"
	return str(data)

@api.route('/viewplace')
def viewplace():
	data={}
	q="select * from place"
	res=select(q)
	if res:
		data['data']=res
		data['status']="success"
		data['method']='viewplace'
	return str(data)


@api.route('/searchplace')	
def searchplace():
	data={}
	search='%'+request.args['search']+'%'
	q="SELECT * FROM `place` where place like '%s'"%(search)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
		data['method']='search'
	else:
		data['status']="failed"
		
	return str(data)


@api.route('/Viewclub')	
def Viewclub():
	data={}
	q="select * from club inner join login using (login_id) where usertype='club'"
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	return str(data)


@api.route('/Addclub')	
def Addclub():
	data={}
	cid=request.args['cid']
	login_id=request.args['login_id']
	q="insert into joinclub values(null,'%s','%s',curdate(),'join')"%(login_id ,cid)
	insert(q)
	data['status']="success"
	return str(data)


@api.route('/Viewevent')
def Viewevent():
	data={}
	q="select * from event"
	print(q)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	return str(data)

@api.route('/Viewtrip')	
def Viewtrip():
	data={}
	q="select * from trip inner join club using (club_id)"
	print(q)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	return str(data)

@api.route('/sendrequest')
def sendrequest():
	data={}
	tid=request.args['tid']
	login_id=request.args['login_id']
	
	q="insert into request values(null,'%s','%s','requested')"%(login_id,tid)
	insert(q)
	data['status']="success"
	return str(data)

@api.route('/viewrequest')	
def viewrequest():
	data={}
	login_id=request.args['login_id']
	q="select * from request inner join trip using (trip_id) where user_id=(select login_id from user where login_id='%s')"%(login_id)
	res=select(q)
	print(q)
	if res:
		data['data']=res
		data['status']="success"
	return str(data)

@api.route('/uploadimage',methods=['get','post'])
def uploadimage():
	data={}
	
	uid=request.form['login_id']
	tid=request.form['tid']

	img=request.files['image']
	path="static/image/"+str(uuid.uuid4())+img.filename
	img.save(path)

	print(uid,tid)

	q="insert into images values(null,'%s',(select user_id from user where login_id='%s'),'%s')"%(tid,uid,path)
	insert(q)
	data['status']='success'
	return str(data)


@api.route('/viewworkshop')	
def viewworkshop():
	data={}
	q="select * from workshop"
	res=select(q)
	if res:
		
		data['status']='success'
		data['data']=res
	return str(data)



@api.route('/Viewhotels')	
def Viewhotels():
	data={}
	q="select * from hotel"
	res=select(q)
	if res:
		
		data['status']='success'
		data['data']=res
	return str(data)


@api.route('/viewusers')
def viewusers():
	data={}
	login_id=request.args['lid']
	q="select * from user where login_id='%s'"%(login_id)
	res=select(q)
	if res:
		data['status']='success'
		data['data']=res
		data['method']='viewusers'
	return str(data)

@api.route('/updateuser')	
def updateuser():
	data={}
	name=request.args['name']
	place=request.args['place']
	phone=request.args['Phone']
	email=request.args['email']
	licence=request.args['licence']
	login_id=request.args['login_id']

	q="update `user` set fname='%s',place='%s',phone='%s',email='%s',license='%s' where login_id='%s'"%(name,place,phone,email,licence,login_id)
	update(q)
	print(q)
	data['status']='success'
	data['method']='updateuser'
	return str(data)


@api.route('/chatdetail')
def chatdetail():
	data={}
	sender_id=request.args['sender_id']
	receiver_id=request.args['receiver_id']
	# q="select * from chat where (sender_id='%s' and receiver_id in (SELECT user_id FROM request WHERE trip_id='%s' AND user_id !='%s')) or (sender_id in (SELECT user_id FROM request WHERE trip_id='%s' AND user_id !='%s') and receiver_id='%s')" %(sender_id,receiver_id,sender_id,receiver_id,sender_id,sender_id)
	q="SELECT * FROM chat WHERE (sender_id='%s' AND receiver_id='%s') OR (sender_id IN (SELECT user_id FROM request WHERE trip_id='%s' AND user_id !='%s') AND receiver_id='%s')" %(sender_id,receiver_id,receiver_id,sender_id, receiver_id)
	print(q)
	res=select(q)
	if res:
		data['status']='success'
		data['data']=res
	else:
		data['status']='failed'
	data['method']='chatdetail'
	return str(data)



@api.route('/chat',methods=['get','post'])
def chat():
	data={}
	
	sender_id=request.args['sender_id']
	receiver_id=request.args['receiver_id']
	details=request.args['details']

	

	q="insert into chat values(null,'%s','%s','%s',curdate())"%(sender_id,receiver_id,details)
	insert(q)


	data['status']='success'
	data['method']='chat'


	return str(data)	


@api.route('/viewchatperson')
def viewchatperson():
	data={}
	login_id=request.args['login_id']
	q="SELECT * FROM chat INNER JOIN USER ON user.login_id=chat.sender_id where login_id='%s'"%(login_id)
	res=select(q)
	print(q)
	if res:
		data['status']='success'
		data['data']=res
	
	return str(data)


@api.route('/ViewPackages')
def ViewPackages():
	data={}
	pid=request.args['place_id']

	q="SELECT * FROM `package` INNER JOIN `place` using (`place_id`) where place_id='%s'"%(pid)

	res=select(q)
	print(q)
	if res:
		data['status']='success'
		data['data']=res
	
	return str(data)
			

			

			

			

			

			

	



		
	

	


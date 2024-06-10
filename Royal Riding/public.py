from flask import *
import uuid
from database import*
public=Blueprint('public',__name__)


@public.route('/')
def home():
	return render_template('home.html')

@public.route('/login',methods=['post','get'])
def login():
	if "login" in request.form:
		u=request.form['uname']
		p=request.form['pwd']
		q="select * from login where username='%s' and password='%s'"%(u,p)
		res=select(q)
		if res:

			session['login_id']=res[0]['login_id']
			lid=session['login_id']



			if res[0]['usertype']=="admin":
				return redirect(url_for('admin.admin_home'))

			if res[0]['usertype']=="club":

				q="select * from club where login_id='%s'"%(lid)
				res=select(q)

				if res:
					session['club_id']=res[0]['club_id']
					cid=session['club_id']
	
				return redirect(url_for('club.club_home'))
				
			
	return render_template('login.html')

@public.route('/registration',methods=['post','get'])
def registration():
	if "registartion" in request.form:
		f=request.form['fname']
		p=request.form['phone']
		e=request.form['email']
		i=request.files['img']
		path='static/image/'+str(uuid.uuid4())+i.filename
		i.save(path)
		la=request.form['lat']
		lo=request.form['lon']
		u=request.form['uname']
		p=request.form['pwd']
		q="select * from login where username='%s' and password='%s'"%(u,p)
		res=select(q)
		if res:
			flash('already exist')

		else:
			
			q="insert into login values(null,'%s','%s','pending')"%(u,p)
			id=insert(q)
			q="insert into club values(null,'%s','%s','%s','%s','%s','%s','%s','pending')"%(id,f,p,e,path,la,lo)
			insert(q)
			flash('successfully')


	return render_template('registration.html')
			
			


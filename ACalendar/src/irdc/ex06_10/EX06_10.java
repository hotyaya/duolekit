/*here one-time clock is TimePickerDialog without layout xxx.xml.
 *     repeat-clock   is AlertDialog      with a  layout timeset.xml.
      
 qixy: requestCode is very important in that it can differentiate these two clocks,when you cancel either 
clock, requestCode is the only thing to differentiate them.      
       */
package irdc.ex06_10;
import java.util.Calendar;

import net.blogjava.mobile.calendar.R;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class EX06_10 extends Activity
{
  TextView setTime1;
  TextView setTime2;
  Button mButton1;
  Button mButton2;
  Button mButton3;
  Button mButton4;
  Calendar c=Calendar.getInstance();
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main3);
/*the following "setTime1" is the first red font.*/    
    setTime1=(TextView) findViewById(R.id.setTime1);
    mButton1=(Button)findViewById(R.id.mButton1);
    mButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
/*public void setTimeInMillis (long milliseconds) 
Sets the time of this Calendar.
Parameters:
milliseconds  the time as the number of milliseconds since Jan. 1, 1970. 
*/        
    	  c.setTimeInMillis(System.currentTimeMillis());
        int mHour=c.get(Calendar.HOUR_OF_DAY);
        int mMinute=c.get(Calendar.MINUTE);
/*android.app.TimePickerDialog: A dialog that prompts the user for the time of day using a TimePicker. 
public TimePickerDialog (Context context, TimePickerDialog.OnTimeSetListener callBack, int hourOfDay,
int minute, boolean is24HourView) 
Parameters:
context  Parent. 
callBack  How parent is notified. 
hourOfDay  The initial hour. 
minute  The initial minute. 
is24HourView  Whether this is a 24 hour view, or AM/PM.  
qixy: we don't need a xxx.xml to layout. We use TimePickerDialog's default layout. 
*/        
        new TimePickerDialog(EX06_10.this,
          new TimePickerDialog.OnTimeSetListener()
          {                
/*The callback interface used to indicate the user is done filling in the time (they clicked on the
'Set' button). 
 public abstract void onTimeSet (TimePicker view, int hourOfDay, int minute) 
Parameters
view  The view associated with this listener. 
hourOfDay  The hour that was set. 
minute  The minute that was set.  
 */          
            public void onTimeSet(TimePicker view,int hourOfDay,int minute)
            {
              c.setTimeInMillis(System.currentTimeMillis());
              c.set(Calendar.HOUR_OF_DAY,hourOfDay);
              c.set(Calendar.MINUTE,minute);
              c.set(Calendar.SECOND,0);
              c.set(Calendar.MILLISECOND,0);
/*   background knowledge: public abstract void Context.sendBroadcast (Intent intent) 
Broadcast the given intent to all interested BroadcastReceivers.

public static PendingIntent getBroadcast (Context context, int requestCode, Intent intent, int flags) 
Retrieve a PendingIntent that will perform a broadcast, like calling Context.sendBroadcast().
qixy: Retrieve a PendingIntent that will perform a broadcast to broadcast the given intent to all 
interested BroadcastReceivers. , like calling Context.sendBroadcast().
  
  qixy:here CallAlarm is a BroadReceiver.
 note that if we change the following to 
 Intent intent = new Intent(EX06_10.this, AlertAlarm.class); then it can not work, because AlertAlarm is an
 ordinary activity. so the principle of alarm is like this: must use broadcastReceiver.
 
qixy: Intent intent = new Intent(EX06_10.this, CallAlarm.class); means intent is associated with a CallAlarm,
when there is an alarm in the future, PendingIntent associated with this ordinary intent will broadcast 
this ordinary intent which will be received by CallAlarm which is a BroadcastReceiver.
    
   
for reference:  The intent which will be broadcast goes to a broadcast receiver that you registered with
registerReceiver(BroadcastReceiver, IntentFilter) or through the <receiver> tag in an
AndroidManifest.xml file. 


public Intent (Context packageContext, Class<?> cls) 
Create an intent for a specific component. All other fields (action, data, type, class) are null, though
they can be modified later with explicit calls. This provides a convenient way to create an intent that
is intended to execute a hard-coded class name, rather than relying on the system to find an appropriate
class for you; 

Parameters
packageContext  A Context of the application package implementing this class. 
cls  The component class that is to be used for the intent. 
   */              
              Intent intent = new Intent(EX06_10.this, CallAlarm.class);
/* qixy: getBroadcast will generate an intent, but not ordinary intent, a pendingIntent. Remember sending SMS
 by broadcast with PendingIntent.then pendingIntent will broadcast, then CallAlarm will receive. this is 
 strategy.

 PendingIntent android.app.PendingIntent.getBroadcast(Context context, int requestCode, Intent intent, int flags)

public static PendingIntent getBroadcast (Context context, int requestCode, Intent intent, int flags) 
Retrieve a PendingIntent that will perform a broadcast, like calling Context.sendBroadcast().

Parameters
context  The Context in which this PendingIntent should perform the broadcast. 
requestCode  Private request code for the sender 
intent  The Intent to be broadcast. 
flags  May be FLAG_ONE_SHOT, FLAG_NO_CREATE, FLAG_CANCEL_CURRENT, FLAG_UPDATE_CURRENT, or any of
the flags as supported by Intent.fillIn() to control which unspecified parts of the intent that
can be supplied when the actual send happens. 

Returns
Returns an existing or new PendingIntent matching the given parameters. May
return null only if FLAG_NO_CREATE has been supplied.

qixy: requestCode is very important in that it can differentiate these two clocks,when you cancel either 
clock, requestCode is the only thing to differentiate them.
*/              
              PendingIntent sender=PendingIntent.getBroadcast(EX06_10.this,
                            0, intent, 0);
              AlarmManager am;
              am = (AlarmManager)getSystemService(ALARM_SERVICE);
/*void android.app.AlarmManager.set(int type, long triggerAtTime, PendingIntent operation)

public void set (int type, long triggerAtTime, PendingIntent operation) 
Schedule an alarm. Note: for timing operations (ticks, timeouts, etc) it is easier and much more efficient
to use Handler. If there is already an alarm scheduled for the same IntentSender, it will first be canceled. 

If the time occurs in the past, the alarm will be triggered immediately. If there is already an alarm for
this Intent scheduled (with the equality of two intents being defined by filterEquals(Intent)), then it
will be removed and replaced by this one. 

Parameters
type  One of ELAPSED_REALTIME, ELAPSED_REALTIME_WAKEUP, RTC or RTC_WAKEUP. 
triggerAtTime  Time the alarm should go off, using the appropriate clock (depending on the alarm type). 
operation  Action to perform when the alarm goes off; typically comes from IntentSender.getBroadcast(). 

ʱ�ӷ�Ϊϵͳʱ�Ӻ�Ӳ��ʱ�����֡�Ӳ��ʱ�ӿ��������ڳ�����Դ��ͨ����ť�۵�أ������ۼ�ʱ�䣬��ϵͳʱ�����ϵͳ����֮�����ã�
����һ��linuxϵͳ�ٿ�ʼʱ����һ��ϵͳʱ����Ӳ��ʱ��ͬ���Ķ�����hwclodk -w��,��������ں�������ϸ���ͣ���ν��ʵʱʱ�Ӽ�
���������������һ���豸���磨poweroff��֮���ϵͳʱ��ά��ģ�顣

RTC��Ӣ��ȫ����Real-Time Clock,���������ʵʱʱ��оƬ.

����RTC��PC�����ϵľ�����ص�·��ɵ�ʱ�ӵ�·���������壬RTC����8254��·��Ƶ����һ��Ƶ�ʽϵ�һ���OS(ϵͳ)ʱ��TSC
������4��Alarm���͡����ѡ�񽫾�������set�����д��ݵ�ʱ��ֵ����ʲô�����ض���ʱ�������ʱ�����ţ�
RTC_WAKEUP
��ָ����ʱ�̣�����Alarm��ʱ�򣩣������豸������Intent��
RTC
��һ����ʽ��ʱ�䴥��Intent�����������豸��
ELAPSED_REALTIME
���豸������������ŵ�ʱ��ﵽ��ʱ�䣬��ô����Intent�����������豸�����ŵ�ʱ������豸˯�ߵ��κ�ʱ�䡣ע��һ����ǣ�
ʱ�����ŵļ�������Դ������һ����������
ELAPSED_REALTIME_WAKEUP
���豸�����󣬴ﵽ���ŵ���ʱ��������Ҫ�������豸������Intent��
qixy: we just use RTC_WAKEUP, not intensively researched.Allegedly,RTC_WAKEUP means ��ʹϵͳ����ʱͬ����
����set. while RTC ϵͳ����ʱ��������set.
qixy:the following statement means when c.getTimeInMillis(), PendingIntent will broadcast intent,
CallAlarm will receive it,  
*/              
              am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
              String tmpS=format(hourOfDay)+": "+format(minute);
              setTime1.setText(tmpS);
              Toast.makeText(EX06_10.this,"��������ʱ��Ϊ "+tmpS,Toast.LENGTH_SHORT).show();
            }          
          },mHour,mMinute,true).show();
      }
    });
        
    mButton2=(Button) findViewById(R.id.mButton2);
    mButton2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        Intent intent = new Intent(EX06_10.this, CallAlarm.class);
        PendingIntent sender=PendingIntent.getBroadcast(EX06_10.this,
                      0, intent, 0);
        AlarmManager am;
        am =(AlarmManager)getSystemService(ALARM_SERVICE);
/*void android.app.AlarmManager.cancel(PendingIntent operation)
public void cancel (PendingIntent operation) 
Remove any alarms with a matching Intent. Any alarm, of any type, whose Intent
matches this one (as defined by filterEquals(Intent)), will be canceled.
Parameters:
operation  IntentSender which matches a previously added IntentSender. 
*/        
        am.cancel(sender);
        Toast.makeText(EX06_10.this,"����ʱ����", Toast.LENGTH_SHORT).show();
        setTime1.setText("Ŀǰ������");
      }
    });
    setTime2=(TextView) findViewById(R.id.setTime2);
    LayoutInflater factory = LayoutInflater.from(this);
/*note that repeat-clock 's layout is made by ourself. determined by timeset.xml.actually it is
an AlertDialog */    
    final View setView = factory.inflate(R.layout.timeset,null);
/* timePicker's time is set later by tPicker.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));  */    
    final TimePicker tPicker=(TimePicker)setView.findViewById(R.id.tPicker);
    tPicker.setIs24HourView(true);
    final AlertDialog di=new AlertDialog.Builder(EX06_10.this)
          .setIcon(R.drawable.clock)
          .setTitle("qixy")
          .setView(setView)
          .setPositiveButton("ȷ��",new DialogInterface.OnClickListener()
           {
             public void onClick(DialogInterface dialog, int which)
             {
               EditText ed=(EditText)setView.findViewById(R.id.mEdit);
               int times=Integer.parseInt(ed.getText().toString())*1000;
               c.setTimeInMillis(System.currentTimeMillis());
/* Integer android.widget.TimePicker.getCurrentHour()
Returns: The current hour (0-23). */               
               c.set(Calendar.HOUR_OF_DAY,tPicker.getCurrentHour());
               c.set(Calendar.MINUTE,tPicker.getCurrentMinute());
               c.set(Calendar.SECOND,0);
               c.set(Calendar.MILLISECOND,0);
/*qixy: after we click the button,the following group of statements are executed. If then,we come to the 
android desktop,when time is up, EX06_10 is brought up, also CallAlarm and AlarmAlert are brought up.
because Intent intent = new Intent(EX06_10.this, CallAlarm.class);   */               
               Intent intent = new Intent(EX06_10.this, CallAlarm.class);
               PendingIntent sender = PendingIntent.getBroadcast(EX06_10.this,
                             1, intent, 0);
               AlarmManager am;
               am = (AlarmManager)getSystemService(ALARM_SERVICE);
/*void android.app.AlarmManager.setRepeating(int type, long triggerAtTime,
long interval, PendingIntent operation)
Schedule a repeating alarm. 
Parameters:
type:  One of ELAPSED_REALTIME, ELAPSED_REALTIME_WAKEUP}, RTC or RTC_WAKEUP. 
triggerAtTime:  Time the alarm should first go off, using the appropriate clock
     (depending on the alarm type). 
interval:  Interval between subsequent repeats of the alarm. 
operation:  Action to perform when the alarm goes off; typically comes from
      IntentSender.getBroadcast(). 
*/               
               am.setRepeating(AlarmManager.RTC_WAKEUP,
                         c.getTimeInMillis(),times,sender);
               String tmpS=format(tPicker.getCurrentHour())+": "+
                           format(tPicker.getCurrentMinute());
               setTime2.setText("��������ʱ��Ϊ "+tmpS+"���"+times/1000+"��");
               Toast.makeText(EX06_10.this,"��������ʱ��Ϊ "+tmpS+"��� "+times/1000+"��",
            		   Toast.LENGTH_SHORT).show();
             }
           })
          .setNegativeButton("ȡ��",new DialogInterface.OnClickListener()
           {
             public void onClick(DialogInterface dialog, int which)
             {
             }
           }).create();
    mButton3=(Button) findViewById(R.id.mButton3);
    mButton3.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        c.setTimeInMillis(System.currentTimeMillis());
        tPicker.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
        tPicker.setCurrentMinute(c.get(Calendar.MINUTE));
        di.show();
      }
    });
        
    mButton4=(Button) findViewById(R.id.mButton4);
    mButton4.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        Intent intent = new Intent(EX06_10.this, CallAlarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(EX06_10.this,1, intent, 0);
        AlarmManager am;
        am = (AlarmManager)getSystemService(ALARM_SERVICE);
       
        am.cancel(sender);
        Toast.makeText(EX06_10.this,"����ʱ����",Toast.LENGTH_SHORT).show();
        setTime2.setText("Ŀǰ������");
      }
    });
  }
  
  private String format(int x)
  {
    String s=""+x;
    if(s.length()==1) s="0"+s;
    return s;
  }
}
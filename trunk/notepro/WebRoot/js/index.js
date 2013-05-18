
/** Open the quick tips */
Ext.QuickTips.init();
Ext.form.Field.prototype.msgTarget = 'side';
/** redefine the blank image url */
var imagePath = 'http://localhost:8080/notepro/ext/resources/images';
Ext.BLANK_IMAGE_URL = imagePath+'/default/s.gif';



var tools = [{
    id:'gear',
    handler: function(){
        Ext.Msg.alert('Message', 'This function need to be modified.');
    }
},{
    id:'close',
    handler: function(e, target, panel){
        panel.ownerCt.remove(panel, true);
    }
}];

/** 定义中心区域, 本系统的核心区域, 所有打开的Tab都将在该区域展示 */
var centerRegion = new Ext.TabPanel({
    region:'center',
    deferredRender:false,
    activeTab:0,
    enableTabScroll:true,
    listeners:{
    	remove: function(tp, c){
    		c.hide();
    	}
    },
    hideBorders:true,
    autoDestroy: false,
    items:[
				{
					xtype:'tabpanel' ,
					title: '办公桌面',
					iconCls: 'tabs',
					closable: false,
					frame:false,				
					html:"<iframe scrolling='auto' frameborder='0' width='100%' height='100%' src='users/desktool/desktop.jsp'> </iframe>",
					items:[]
				}
          ]
});

/** 这里是页面展示的开始 */
Ext.onReady(function(){
	/** 处理ie提交数据中文乱码问题 */
    Ext.lib.Ajax.defaultPostHeader += '; charset=utf-8';
    new Ext.Viewport({ //实例化布局对象
    	  layout:'border', 
    	  items:[
    	        {
	                region:'west',
	                title:'管理界面',
	                id:'west-panel',	                
	                split:true,
	                width: 200,
	                minSize: 170,
	                maxSize: 400,
	                collapsible: true,
	                margins:'35 0 5 5',
	                cmargins:'35 5 5 5',
	                layout:'accordion',	            
	                layoutConfig:{
	                    animate:true
	                },
	                items: [
						   {
							    title:'笔记管理',
							    border:false,
							    autoScroll:true,
							    iconCls:'notes',
							    items: [treepanel]
							    
							},
							{
							    title:'我的群组',
							    border:false,
							    autoScroll:true,					
							    iconCls:'group',
							    items: [grouppanel]
							    
							},
							{
							    title:'日常工具',
							    layout:'absolute',
							    border:false,
							    autoScroll:true,
							    iconCls:'tools',
							    items: [
										{
											    xtype:'button',								
											    text: '<h1><font color="red"><center>日程日历</center></font></h1>',												    
											    iconCls: 'calendar',											    
											    tooltip:"这是一个日程管理工具，您可以在这里安排您的一切",											    
											    tooltipType:"title",
											    height:'20%',
											    width : '70%',
											    x:'15%',
											    y:'5%',
											    handler: function(){			
													if(centerRegion.findById("calendartab"))
													{
														centerRegion.setActiveTab("calendartab");
														return ;
													}
													centerRegion.add({
														title:"NoteBook日历",
														id:"calendartab",
														html:"<iframe scrolling='auto' frameborder='0' width='100%' height='100%' src='users/toolbar/calendar.jsp'> </iframe>",
														closable:true
													});
													centerRegion.setActiveTab("calendartab");
										  		}
											},
											{
												xtype:'button',
											    text: '<h1><font color="red"><center>天气指南</center></font></h1>',
											    tooltip:"这是一个天气预报工具",											    
											    tooltipType:"title",
											    iconCls: 'calendar',																    
											    height:'20%',
											    width : '70%',											   	
											    x:'15%',
											    y:'28%',
											    handler: function(){			
													if(centerRegion.findById("weathertap"))
													{
														centerRegion.setActiveTab("weathertap");
														return ;
													}
													centerRegion.add({
														title:"天气指南",
														id:"weathertap",
														html:"<iframe scrolling='auto' frameborder='0' width='100%' height='100%' src='dailylife/weather.action'> </iframe>",
														closable:true
													});
													centerRegion.setActiveTab("weathertap");
										  		}
											},
											{
												xtype:'button',
											    text: '我的游戏',
											    iconCls: 'calendar',											    
											    height:'20%',
											    width : '70%',											   
											    x:'15%',
											    y:'51%'
											},
											{
												xtype:'button',
											    text: '我的e家',
											    iconCls: 'calendar',											    
											    height:'20%',
											    width : '70%',											   	
											    x:'15%',
											    y:'74%'
											}
							           ]
							    
							},
		                    {
			                    title:'程序设置',
			                    html: '<html><body>  设置：<input type="text" name="firstname" /></body></html>',
			                    border:false,
			                    autoScroll:true,
			                    iconCls:'settings'
		                    }
		                    ]
    	        	},
    	        	centerRegion
    	        ]
     });
    //设置皮肤的核心代码
    //Ext.util.CSS.swapStyleSheet('viewport','ext/resources/css/reset-min.css');
    
});








//此面板未使用

var southRegion = {
    region: 'south',
    xtype: 'panel',
    split: false,
    height: 30,
    //collapsible: true,
    //html: '',
    //margins: '0 0 0 0',
	bbar:[
	  new Ext.Toolbar.TextItem('工具栏 '),
	  {xtype:"tbfill"},
	  new Ext.Toolbar.TextItem('大众平台'),
	  {xtype:'tbseparator'},
	  new Ext.Toolbar.TextItem('技术支持 <a href=http://www.163.com>个人作品</a>'),
	  {pressed:false,text:'与我联系',iconCls: 'tabs'}
	]
	
};
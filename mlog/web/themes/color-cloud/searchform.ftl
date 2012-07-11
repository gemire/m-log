<form method="get" id="searchform" >
	<input type="text" size="20" class="txt" name="s" id="s" value="${searchInfo}"  onblur="if(this.value=='') this.value='${queryString?default(searchInfo)}';" onfocus="if(this.value=='${queryString?default(searchInfo)}') this.value='';"/><input type="submit" class="btn" id="topbtn" name="submit" value=" ${search} " />
</form>

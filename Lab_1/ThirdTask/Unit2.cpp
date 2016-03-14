//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop
#include <dos.h>
#include "Unit2.h"
#include <stdio.h>
#include <time.h>
#include <stdlib.h>
//---------------------------------------------------------------------------
#pragma package(smart_init)
#pragma resource "*.dfm"
TForm2 *Form2;
//---------------------------------------------------------------------------
__fastcall TForm2::TForm2(TComponent* Owner)
        : TForm(Owner)
{

srand(time(NULL));
Timer2->Enabled = false;


}
//---------------------------------------------------------------------------

void __fastcall TForm2::Timer1Timer(TObject *Sender)
{
   struct time t;
   struct date d;

   gettime(&t);
   getdate(&d);

   char buf[30];

   sprintf(buf, "%02d-%02d-%02d %02d:%02d:%02d", d.da_day, d.da_mon, d.da_year,t.ti_hour,t.ti_min,t.ti_sec);
   Edit1->Text = buf;


}
//---------------------------------------------------------------------------//---------------------------------------------------------------------------}
//---------------------------------------------------------------------------
void __fastcall TForm2::PaintBox1Paint(TObject *Sender)
{
PaintBox1->Canvas->Pen->Color = clBlack;
PaintBox1->Canvas->Brush->Style = bsCross;
PaintBox1->Canvas->Brush->Color = clBtnFace;
TRect rect = Rect(0, 0, 240, 240);
PaintBox1->Canvas->FillRect(rect);
//PaintBox1->Canvas->Rectangle(0, 0, 240, 240);
/*for(int i = 0; i <= 240; i += 10){
PaintBox1->Canvas->MoveTo(0,i);
PaintBox1->Canvas->LineTo(240,i);
PaintBox1->Canvas->MoveTo(i,0);
PaintBox1->Canvas->LineTo(i,240);
}
*/
}
//---------------------------------------------------------------------------
void __fastcall TForm2::Timer2Timer(TObject *Sender)
{
        static int x = 0;
        int sign = rand() % 2 == 0 ? 1 : -1;
        static int y = ((rand() % 2) * sign + 120);

        PaintBox1->Canvas->Pen->Color = clRed;
        //PaintBox1->Canvas->Brush->Style = bsHorizontal;
        Panel1->Height = y;
        
        PaintBox1->Canvas->MoveTo(x , y);
        x =((rand() % 3) + x);
        y = ((rand() % 40) * sign + 120);

        PaintBox1->Canvas->LineTo(x,y);            
}
//---------------------------------------------------------------------------
void __fastcall TForm2::Button1Click(TObject *Sender)
{
        Timer2->Enabled = true;
        Button1->Enabled = false;
}
//---------------------------------------------------------------------------
void __fastcall TForm2::Button2Click(TObject *Sender)
{
        Button1->Enabled = true;
        Timer2->Enabled = false;
}
//---------------------------------------------------------------------------
void __fastcall TForm2::Button3Click(TObject *Sender)
{
Form2->Close();
}
//---------------------------------------------------------------------------

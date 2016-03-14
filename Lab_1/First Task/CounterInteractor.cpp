//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop

#include "CounterInteractor.h"
//---------------------------------------------------------------------------
#pragma package(smart_init)
#pragma link "sButton"
#pragma link "sEdit"
#pragma link "sLabel"
#pragma resource "*.dfm"
TFirstTask *FirstTask;
//---------------------------------------------------------------------------
__fastcall TFirstTask::TFirstTask(TComponent* Owner)
        : TForm(Owner)
{
        printCounter();
        this->counter = 0;
}
//---------------------------------------------------------------------------
void __fastcall TFirstTask::onUpButtonClick(TObject *Sender)
{
        ++this->counter;
        printCounter();
}
//---------------------------------------------------------------------------
void __fastcall TFirstTask::onDownButtonClick(TObject *Sender)
{
        --this->counter;
        printCounter();
}
//---------------------------------------------------------------------------
void TFirstTask::printCounter() {
      counterEditor->Text = this->counter;
}
void __fastcall TFirstTask::onExitButtonClick(TObject *Sender)
{
        Close();
}
//---------------------------------------------------------------------------

void __fastcall TFirstTask::onUpDownControlChange(TObject *Sender,
      bool &AllowChange, short NewValue, TUpDownDirection Direction)
{
        this->counter = NewValue;
        printCounter();
}
//---------------------------------------------------------------------------

